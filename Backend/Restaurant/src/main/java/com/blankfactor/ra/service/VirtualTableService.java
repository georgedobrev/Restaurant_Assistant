package com.blankfactor.ra.service;

import com.blankfactor.ra.exceptions.custom.VirtualTableException;
import com.blankfactor.ra.model.VirtualTable;

import java.util.Map;

public interface VirtualTableService {
    VirtualTable createVirtualTable(Integer restaurantId, VirtualTable virtualTable);

    VirtualTable updateVirtualTableByVirtualTableId(Integer restaurantId, Integer virtualTableId, VirtualTable virtualTable) throws VirtualTableException;

    Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId);

    void deleteVirtualTable(Integer restaurantId, VirtualTable virtualTable);

    VirtualTable getVirtualTableByAppTableNumber(Integer restaurantId, Integer tableNumber);

    void save(VirtualTable virtualTable);
}
