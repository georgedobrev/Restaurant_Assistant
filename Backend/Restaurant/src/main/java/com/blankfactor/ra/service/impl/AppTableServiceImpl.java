package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.exceptions.custom.QRCodeException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import com.blankfactor.ra.service.RestaurantService;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {
    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Override
    public List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTableDto> appTablesDto) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        List<AppTable> appTables = appTablesDto.stream()
                .map(appTableDto -> AppTable.builder()
                        .tableNumber(appTableDto.getTableNumber())
                        .capacity(appTableDto.getCapacity())
                        .restaurant(restaurant)
                        .build())
                .collect(Collectors.toList());

        try {
            qrCodeService.createQRCodesForTables(restaurant, appTables);
        } catch (IOException | WriterException | NoSuchAlgorithmException e) {
            throw new QRCodeException("Could not create QR codes");
        }

        restaurant.setTablesCount(restaurant.getTablesCount() + appTablesDto.size());
        restaurantRepository.save(restaurant);

        return appTableRepository.saveAll(appTables);
    }

    @Transactional
    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) {
        AppTable existingTable = getTableByTableNumber(restaurantId, tableNumber);

        existingTable.setTableNumber(updatedTableDto.getTableNumber());
        existingTable.setOccupied(updatedTableDto.isOccupied());
        existingTable.setCapacity(updatedTableDto.getCapacity());
        existingTable.setVirtualTable(updatedTableDto.isVirtualTable());
        existingTable.setActive(updatedTableDto.isActive());

        appTableRepository.save(existingTable);

        return existingTable;
    }

    @Override
    public List<AppTable> getTablesByRestaurantId(Integer restaurantId) {
        return appTableRepository.findByRestaurantId(restaurantId);
    }


    @Override
    public void removeTableByName(Integer restaurantId, Integer tableNumber) {
        AppTable existingTable = getTableByTableNumber(restaurantId, tableNumber);
        appTableRepository.delete(existingTable);
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                .orElseThrow(() -> new AppTableException("App table " + tableNumber + " not found"));
    }
}