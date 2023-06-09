package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import com.blankfactor.ra.service.RestaurantService;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final RestaurantService restaurantService;

    @Override
    public List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTable> appTables) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        List<AppTable> tables = appTables.stream()
                .peek(t -> t.setRestaurant(restaurant))
                .collect(Collectors.toList());

        try {
            qrCodeService.createQRCodesForTables(restaurant, tables);
        } catch (IOException | WriterException e) {
            throw new RuntimeException(e);
        }

        appTableRepository.saveAll(tables);
        return tables;
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) throws Exception {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);
    }

    @Override
    public List<AppTable> getTablesByRestaurantId(Integer restaurantId) {
        return appTableRepository.findByRestaurantId(restaurantId);
    }

    @Transactional
    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) throws Exception {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);

        existingTable.setTableNumber(updatedTableDto.getTableNumber());
        existingTable.setOccupied(updatedTableDto.isOccupied());
        existingTable.setCapacity(updatedTableDto.getCapacity());
        existingTable.setVirtualTable(updatedTableDto.isVirtualTable());
        existingTable.setActive(updatedTableDto.isActive());

        appTableRepository.save(existingTable);

        return existingTable;
    }

    @Override
    public void removeTableByName(Integer restaurantId, Integer tableNumber) throws Exception {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);
        appTableRepository.delete(existingTable);
    }
}

