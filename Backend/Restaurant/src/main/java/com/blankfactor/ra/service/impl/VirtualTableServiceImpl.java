package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.VirtualTableDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.VirtualTableException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.VirtualTable;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.VirtualTableRepository;
import com.blankfactor.ra.service.VirtualTableService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class VirtualTableServiceImpl implements VirtualTableService {
    private final AppTableRepository appTableRepository;
    private final VirtualTableRepository virtualTableRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public VirtualTable createVirtualTable(Integer restaurantId, VirtualTableDto virtualTableDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));

        checkIfTableNumbersExistsAndSetVirtualTable(virtualTableDto.getTableNumbers(), restaurantId, true);

        String tableNumbers = mapTableNumbersToString(virtualTableDto.getTableNumbers());
        VirtualTable virtualTable = VirtualTable.builder()
                .tableNumbers(tableNumbers)
                .restaurant(restaurant)
                .build();

        return virtualTableRepository.save(virtualTable);
    }

    @Override
    public Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId) {
        return virtualTableRepository.findByRestaurantId(restaurantId)
                .stream()
                .collect(Collectors.toMap(VirtualTable::getId, Function.identity()));
    }

    @Override
    public VirtualTable getVirtualTableByAppTableNumber(Integer restaurantId, Integer tableNumber) {
        List<VirtualTable> virtualTables = virtualTableRepository.findByRestaurantId(restaurantId);

        for (VirtualTable table : virtualTables) {
            List<Integer> tableNumbers = mapVirtualTableNumbersToInteger(table);
            for (Integer virtualTableNumber : tableNumbers) {
                if (Objects.equals(virtualTableNumber, tableNumber)) {
                    return table;
                }
            }
        }
        throw new VirtualTableException("Virtual table with table " + tableNumber + " in it, not found");
    }

    @Override
    public VirtualTable updateVirtualTableByVirtualTableId(Integer restaurantId, Integer virtualTableId, VirtualTable updatedVirtualTable) {
        VirtualTable existingVirtual = virtualTableRepository.findById(virtualTableId)
                .orElseThrow(() -> new VirtualTableException("Virtual table not found"));

        existingVirtual.setTableNumbers(updatedVirtualTable.getTableNumbers());
        virtualTableRepository.save(existingVirtual);
        return existingVirtual;
    }

    @Override
    public void deleteVirtualTable(Integer restaurantId, VirtualTable virtualTable) {
        List<Integer> tableNumbers = mapVirtualTableNumbersToInteger(virtualTable);

        checkIfTableNumbersExistsAndSetVirtualTable(tableNumbers, restaurantId, false);

        virtualTableRepository.delete(virtualTable);
    }

    private void checkIfTableNumbersExistsAndSetVirtualTable(List<Integer> tableNumbers, Integer restaurantId, boolean isVirtual) {
        for (int tableNumber : tableNumbers) {

            AppTable appTable = appTableRepository.findByRestaurantIdAndTableNumberAndDeletedIsFalse(restaurantId, tableNumber)
                    .orElseThrow(() -> new AppTableException("App table " + tableNumber + " not found"));

            if (isVirtual) {
                if (appTable.isVirtualTable()) {
                    throw new VirtualTableException("Table " + tableNumber + " is already assigned as virtual table");
                }
            }

            appTable.setVirtualTable(isVirtual);
            appTableRepository.save(appTable);
        }
    }

    private String mapTableNumbersToString(List<Integer> tableNumbers) {
        return tableNumbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private List<Integer> mapVirtualTableNumbersToInteger(VirtualTable virtualTable) {
        return Arrays.stream(virtualTable.getTableNumbers().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}