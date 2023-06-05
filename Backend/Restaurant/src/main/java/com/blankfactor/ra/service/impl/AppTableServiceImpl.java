package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.dto.QrCodeDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final ModelMapper modelMapper;

    @Override
    public List<QrCodeDto> createTablesForRestaurant(Restaurant restaurant, List<AppTable> appTables) throws Exception {
        List<AppTable> createdTables = appTables.stream().map(table -> {
            table.setRestaurant(restaurant);
            return appTableRepository.save(table);
        }).toList();

        List<Integer> tableNumbers = createdTables.stream().map(AppTable::getTableNumber).toList();
        return qrCodeService.createQRCodeForTables(restaurant.getId(), tableNumbers);
    }

    @Override
    public Optional<AppTable> getTableByTableNumber(Integer restaurantId, Integer tableNumber) {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);
    }

    @Override
    public List<AppTableDto> getTablesByRestaurantId(Integer restaurantId) {
        List<Optional<AppTable>> appTables = appTableRepository.findByRestaurantId(restaurantId);
        return appTables.stream()
                .map(appTable -> modelMapper.map(appTable, AppTableDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTable updatedTable) {
        Optional<AppTable> existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);

        if (existingTable.isPresent()) {
            AppTable tableToUpdate = existingTable.get();
            tableToUpdate.setTableNumber(updatedTable.getTableNumber());
            tableToUpdate.setOccupied(updatedTable.isOccupied());
            tableToUpdate.setActive(updatedTable.isActive());
            tableToUpdate.setVirtualTable(updatedTable.isVirtualTable());
            return appTableRepository.save(tableToUpdate);
        }
        return null;
    }

    @Override
    public boolean removeTableByName(Integer restaurantId, Integer tableNumber) {
        Optional<AppTable> existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);
        if (existingTable.isPresent()) {
            appTableRepository.delete(existingTable.get());
            return true;
        }
        return false;
    }
}

