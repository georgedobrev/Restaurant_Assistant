package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

public interface AppTableService {
    List<AppTable> createTablesForRestaurant(Integer restaurantId, List<AppTable> restaurantTables) throws Exception;

    AppTable getTableByTableNumber(Integer restaurantId, Integer tableNumber) throws Exception;

    List<AppTable> getTablesByRestaurantId(Integer restaurantId);

    AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTableDto updatedTableDto) throws Exception;

    void removeTableByName(Integer restaurantId, Integer tableNumber) throws Exception;

    //List<AppTable> getAllTablesByRestaurantId(int restaurantId);
}
