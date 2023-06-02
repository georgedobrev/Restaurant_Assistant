package com.blankfactor.ra.controller;


import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.impl.RestaurantServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/{restaurantId}/tables")
public class AppTableController {

    private final AppTableService appTableService;
    private final RestaurantServiceImpl restaurantService;


    @PostMapping("/create")
    public ResponseEntity<List<QrCode>> createTable(@PathVariable("restaurantId") Integer restaurantId,
                                                    @RequestBody List<AppTable> appTables) {
        Restaurant restaurant = restaurantService.getRestaurantsById(restaurantId);

        if (restaurant == null){
            return ResponseEntity.notFound().build();
        }
        List<QrCode> qrCodes = appTableService.createTablesForRestaurant(restaurant, appTables);

        return ResponseEntity.ok(qrCodes);
    }

    @GetMapping("/{tableName}")
    public ResponseEntity<AppTable> getTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) {
        AppTable appTable = appTableService.getTableByNumber(restaurantId, tableNumber);

        if (appTable != null) {
            return ResponseEntity.ok(appTable);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<AppTable>> getTablesByRestaurantId(@PathVariable Integer restaurantId) {
        List<AppTable> appTables = appTableService.getTablesByRestaurantId(restaurantId);
        return ResponseEntity.ok(appTables);
    }

    @PutMapping("/{tableName}")
    public ResponseEntity<AppTable> updateTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber, @RequestBody AppTable appTable) {
        AppTable updatedTable = appTableService.updateTableByNumber(restaurantId, tableNumber, appTable);

        if (appTable != null) {
            return ResponseEntity.ok(appTable);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{tableNumber}")
    public ResponseEntity<AppTable> removeTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) {
        boolean removedTable = appTableService.removeTableByName(restaurantId, tableNumber);

        if (removedTable) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

