package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;

import java.util.List;

public interface AppTableService {
    List<AppTable> createTablesForRestaurant(Restaurant restaurant, List<AppTable> restaurantTables) throws Exception;

    AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) throws Exception;

    List<AppTableDto> getTablesByRestaurantId(Integer restaurantId);

    AppTableDto updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) throws Exception;

    boolean removeTableByName(Integer restaurantId, Integer tableNumber) throws Exception;
}
