package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.VirtualTableDto;
import com.blankfactor.ra.exceptions.custom.VirtualTableException;
import com.blankfactor.ra.model.VirtualTable;

import java.util.Map;

public interface VirtualTableService {
    VirtualTable createVirtualTable(Integer restaurantId, VirtualTableDto virtualTableDto);

    VirtualTable getVirtualTableByAppTableNumber(Integer restaurantId, Integer tableNumber);

    Map<Integer, VirtualTable> getAllVirtualTablesByRestaurantId(Integer restaurantId);

    VirtualTable updateVirtualTableByVirtualTableId(Integer restaurantId, Integer virtualTableId, VirtualTable virtualTable) throws VirtualTableException;

    void deleteVirtualTable(Integer restaurantId, VirtualTable virtualTable);
}
