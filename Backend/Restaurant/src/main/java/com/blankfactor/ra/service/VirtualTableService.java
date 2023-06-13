package com.blankfactor.ra.service;

import com.blankfactor.ra.model.VirtualTable;

import java.util.Map;

public interface VirtualTableService {
    VirtualTable createVirtualTable(Integer restaurantId, VirtualTable virtualTable);

    VirtualTable getVirtualTableByTableIdsAndRestaurantId(String tableIds, Integer restaurantId) throws Exception;

    VirtualTable updateVirtualTableByTableNumbers(Integer restaurantId, String tableNumbers, VirtualTable virtualTable) throws Exception;

    Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId);
}
