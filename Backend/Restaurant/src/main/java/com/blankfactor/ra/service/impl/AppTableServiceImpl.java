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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    @Override
    public List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTableDto> appTableDtos) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<AppTable> appTables = createTablesFromDto(restaurant, appTableDtos);

        try {
            qrCodeService.createQRCodesForTables(restaurant, appTables);
        } catch (IOException | WriterException | NoSuchAlgorithmException e) {
            throw new QRCodeException("Could not create QR codes");
        }

        restaurant.setTablesCount(restaurant.getTablesCount() + appTables.size());
        restaurantRepository.save(restaurant);
        return appTableRepository.saveAll(appTables);
    }

    private List<AppTable> createTablesFromDto(Restaurant restaurant, List<AppTableDto> appTableDtos) {
        List<AppTable> appTables = new ArrayList<>();

        for (AppTableDto tableDto : appTableDtos) {
            Integer tableNumber = tableDto.getTableNumber();

            Optional<AppTable> appTable = appTableRepository
                    .findByRestaurantIdAndTableNumberAndDeletedIsTrue(restaurant.getId(), tableNumber);

            if (appTable.isEmpty()) {
                AppTable table = objectMapper.convertValue(tableDto, AppTable.class);
                table.setRestaurant(restaurant);
                appTables.add(table);
            } else {
                appTable.ifPresent(table -> {
                    table.setCapacity(tableDto.getCapacity());
                    table.setDeleted(false);
                    appTableRepository.save(table);
                });
            }
        }
        return appTables;
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) {
        return appTableRepository.findByRestaurantIdAndTableNumberAndDeletedIsFalse(restaurantId, tableNumber)
                .orElseThrow(() -> new AppTableException("App table " + tableNumber + " not found"));
    }

    @Override
    public List<AppTable> getTablesByRestaurantId(Integer restaurantId) {
        return appTableRepository.findByRestaurantIdAndDeletedIsFalse(restaurantId);
    }

    @Transactional
    @Override
    public AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) {
        AppTable existingTable = getTableByTableNumber(restaurantId, tableNumber);

        existingTable.setTableNumber(updatedTableDto.getTableNumber());
        existingTable.setOccupied(updatedTableDto.isOccupied());
        existingTable.setCapacity(updatedTableDto.getCapacity());

        appTableRepository.save(existingTable);

        return existingTable;
    }

    @Transactional
    @Override
    public void deleteTableByTableNumber(Integer restaurantId, Integer tableNumber) {
        AppTable existingTable = getTableByTableNumber(restaurantId, tableNumber);
        appTableRepository.softDeleteAppTable(existingTable.getId());
    }
}

