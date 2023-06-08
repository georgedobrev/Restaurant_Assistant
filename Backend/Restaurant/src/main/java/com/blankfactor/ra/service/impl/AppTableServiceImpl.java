package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.exceptions.custom.AppTableNotFoundException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import com.blankfactor.ra.service.RestaurantService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final ModelMapper modelMapper;
    private final RestaurantService restaurantService;

    @Override
    public List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTable> appTables) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        appTables.forEach(t -> t.setRestaurant(restaurant));
        qrCodeService.createQRCodesForTables(restaurant, appTables);
        appTableRepository.saveAll(appTables);
        return appTables;
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) throws AppTableNotFoundException {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                .orElseThrow(() -> new AppTableNotFoundException("Table with number " + tableNumber + " and restaurant id " + restaurantId + " not found"));
    }

    @Override
    public List<AppTableDto> getTablesByRestaurantId(Integer restaurantId) {
        List<AppTable> appTables = appTableRepository.findByRestaurantId(restaurantId);
        return appTables.stream()
                .map(appTable -> modelMapper.map(appTable, AppTableDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AppTableDto updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) throws AppTableNotFoundException {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                .orElseThrow(() -> new AppTableNotFoundException("Table with number " + tableNumber + " and restaurant id " + restaurantId + " not found"));

        existingTable.setTableNumber(updatedTableDto.getTableNumber());
        existingTable.setOccupied(updatedTableDto.isOccupied());
        existingTable.setCapacity(updatedTableDto.getCapacity());
        existingTable.setVirtualTable(updatedTableDto.isVirtualTable());
        existingTable.setActive(updatedTableDto.isActive());
        appTableRepository.save(existingTable);

        return modelMapper.map(existingTable, AppTableDto.class);
    }

    @Override
    public void removeTableByName(Integer restaurantId, Integer tableNumber) throws AppTableNotFoundException {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                .orElseThrow(() -> new AppTableNotFoundException("Table with number " + tableNumber + " and restaurant id " + restaurantId + " not found"));
        appTableRepository.delete(existingTable);
    }
}

