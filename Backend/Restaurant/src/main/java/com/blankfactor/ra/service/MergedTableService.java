package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.MergedTableDto;
import com.blankfactor.ra.exceptions.custom.MergedTableException;
import com.blankfactor.ra.model.MergedTable;

import java.util.Map;

public interface MergedTableService {
    MergedTable createMergedTable(Integer restaurantId, MergedTableDto mergedTableDto);

    MergedTable getMergedTableByAppTableNumber(Integer restaurantId, Integer tableNumber);

    Map<Integer, MergedTable> getAllMergedTablesByRestaurantId(Integer restaurantId);

    MergedTable updateMergedTableByMergedTableId(Integer restaurantId, Integer virtualTableId, MergedTable mergedTable) throws MergedTableException;

    void deleteMergedTable(Integer restaurantId, MergedTable mergedTable);
}