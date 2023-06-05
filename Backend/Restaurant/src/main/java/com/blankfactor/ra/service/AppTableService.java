package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.dto.QrCodeDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface AppTableService {
    AppTable updateTableByNumber(Integer restaurantId, Integer tableNumber, AppTable appTable);

    List<QrCodeDto> createTablesForRestaurant(Restaurant restaurant, List<AppTable> restaurantTables) throws Exception;

    Optional<AppTable> getTableByTableNumber(Integer restaurantId, Integer tableNumber);

    List<AppTableDto> getTablesByRestaurantId(Integer restaurantId);

    boolean removeTableByName(Integer restaurantId, Integer tableNumber);
}
