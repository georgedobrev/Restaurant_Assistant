package com.blankfactor.ra.service.impl;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.QRCodeService;
import com.google.zxing.WriterException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppTableServiceImpl implements AppTableService {

    private final AppTableRepository appTableRepository;
    private final QRCodeService qrCodeService;
    private final ModelMapper modelMapper;

    @Override
    public List<AppTable> createTablesForRestaurant(Restaurant restaurant, List<AppTable> appTables) {
        List<AppTable> createdTables = appTables.stream().map(table -> {
            table.setRestaurant(restaurant);
            try {
                qrCodeService.createQRCodeForTables(restaurant, table);
            } catch (IOException | WriterException e) {
                throw new RuntimeException(e);
            }
            return table;
        }).toList();
        appTableRepository.saveAll(createdTables);
        return createdTables;
    }

    @Override
    public AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) throws Exception {
        return appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);
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
    public AppTableDto updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) throws Exception {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);

        existingTable.setTableNumber(updatedTableDto.getTableNumber());
        existingTable.setOccupied(updatedTableDto.isOccupied());
        existingTable.setCapacity(updatedTableDto.getCapacity());
        existingTable.setVirtualTable(updatedTableDto.isVirtualTable());
        existingTable.setActive(updatedTableDto.isActive());
        appTableRepository.save(existingTable);

        return modelMapper.map(existingTable, AppTableDto.class);
    }

    @Override
    public boolean removeTableByName(Integer restaurantId, Integer tableNumber) throws Exception {
        AppTable existingTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber).orElseThrow(Exception::new);

        appTableRepository.delete(existingTable);
        return true;
    }
}

