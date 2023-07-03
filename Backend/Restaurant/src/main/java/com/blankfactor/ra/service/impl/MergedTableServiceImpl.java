package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.MergedTableDto;
import com.blankfactor.ra.exceptions.custom.AppTableException;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.MergedTableException;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.MergedTable;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.MergedTableRepository;
import com.blankfactor.ra.service.MergedTableService;
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
public class MergedTableServiceImpl implements MergedTableService {
    private final AppTableRepository appTableRepository;
    private final MergedTableRepository mergedTableRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    @Override
    public MergedTable createMergedTable(Integer restaurantId, MergedTableDto mergedTableDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));

        checkIfTableNumbersExistsAndSetMergedTable(mergedTableDto.getTableNumbers(), restaurantId, true);
        String tableNumbers = mapTableNumbersToString(mergedTableDto.getTableNumbers());

        MergedTable mergedTable = MergedTable.builder()
                .tableNumbers(tableNumbers)
                .restaurant(restaurant)
                .build();

        return mergedTableRepository.save(mergedTable);
    }

    @Override
    public Map<Integer, MergedTable> getAllMergedTablesByRestaurantId(Integer restaurantId) {
        return mergedTableRepository.findByRestaurantId(restaurantId)
                .stream()
                .collect(Collectors.toMap(MergedTable::getId, Function.identity()));
    }

    @Override
    public MergedTable getMergedTableByAppTableNumber(Integer restaurantId, Integer tableNumber) {
        List<MergedTable> mergedTables = mergedTableRepository.findByRestaurantId(restaurantId);

        for (MergedTable table : mergedTables) {
            List<Integer> tableNumbers = mapMergedTableNumbersToInteger(table);
            for (Integer virtualTableNumber : tableNumbers) {
                if (Objects.equals(virtualTableNumber, tableNumber)) {
                    return table;
                }
            }
        }
        throw new MergedTableException("Merged table with table " + tableNumber + " in it, not found");
    }

    @Override
    public MergedTable updateMergedTableByMergedTableId(Integer restaurantId, Integer virtualTableId, MergedTable updatedMergedTable) {
        MergedTable existingMergedTable = mergedTableRepository.findById(virtualTableId)
                .orElseThrow(() -> new MergedTableException("Merged table not found"));

        existingMergedTable.setTableNumbers(updatedMergedTable.getTableNumbers());
        mergedTableRepository.save(existingMergedTable);

        return existingMergedTable;
    }

    @Override
    public void deleteMergedTable(Integer restaurantId, MergedTable mergedTable) {
        List<Integer> tableNumbers = mapMergedTableNumbersToInteger(mergedTable);

        checkIfTableNumbersExistsAndSetMergedTable(tableNumbers, restaurantId, false);

        mergedTableRepository.delete(mergedTable);
    }

    private void checkIfTableNumbersExistsAndSetMergedTable(List<Integer> tableNumbers, Integer restaurantId, boolean isVirtual) {
        for (int tableNumber : tableNumbers) {

            AppTable appTable = appTableRepository.findByRestaurantIdAndTableNumber(restaurantId, tableNumber)
                    .orElseThrow(() -> new AppTableException("App table " + tableNumber + " not found"));

            if (isVirtual) {
                if (appTable.isMergedTable()) {
                    throw new MergedTableException("Table " + tableNumber + " is already assigned as virtual table");
                }
            }

            appTable.setMergedTable(isVirtual);
            appTableRepository.save(appTable);
        }
    }

    private String mapTableNumbersToString(List<Integer> tableNumbers) {
        return tableNumbers.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }

    private List<Integer> mapMergedTableNumbersToInteger(MergedTable mergedTable) {
        return Arrays.stream(mergedTable.getTableNumbers().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
    }
}