package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final ModelMapper modelMapper;

    @Override
    public List<QrCode> createTablesForRestaurant(Restaurant restaurant, List<AppTable> appTables) {
        List<AppTable> createdTables = new ArrayList<>();
        for (AppTable table : appTables) {
            table.setRestaurant(restaurant);
            createdTables.add(createTable(table));
        }
        List<Integer> tableNumbers = createdTables.stream().map(AppTable::getTableNumber).toList();
        return qrCodeService.generateQRCodeForTables(restaurant.getId(), tableNumbers);
    }

    @Override
    public AppTable createTable(AppTable appTable) {
        return appTableRepository.save(appTable);
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);
    }

    @Override
    public List<AppTableDto> getTablesByRestaurantId(Integer restaurantId) {
        List<AppTable> appTables = appTableRepository.findByRestaurantId(restaurantId);
        return appTables.stream()
                .map(appTable -> modelMapper.map(appTable, AppTableDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTable updatedTable) {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);

        if (existingTable != null) {
            existingTable.setTableNumber(updatedTable.getTableNumber());
            existingTable.setOccupied(updatedTable.isOccupied());
            existingTable.setActive(updatedTable.isActive());
            existingTable.setVirtualTable(updatedTable.isVirtualTable());
            return appTableRepository.save(existingTable);
        }
        return null;
    }

    @Override
    public boolean removeTableByName(Integer restaurantId, Integer tableNumber) {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);
        if (existingTable != null) {
            appTableRepository.delete(existingTable);
            return true;
        }
        return false;
    }
}

