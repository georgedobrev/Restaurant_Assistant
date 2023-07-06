package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;

import java.util.List;

public interface AppTableService {
    List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTableDto> appTableDtos);

    AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber);

    List<AppTable> getTablesByRestaurantId(Integer restaurantId);

    AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto);

    void deleteTableByTableNumber(Integer restaurantId, Integer tableNumber);
}
