package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;

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
    public AppTable getTableByNumber(Integer restaurantId, Integer tableNumber) {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);
    }

    @Override
    public List<AppTable> getTablesByRestaurantId(Integer restaurantId) {
        return appTableRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTable updatedTable) {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber);

        if (existingTable != null) {
            updatedTable.setTableNumber(existingTable.getTableNumber());
            updatedTable.setRestaurant(existingTable.getRestaurant());
            return appTableRepository.save(updatedTable);
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

