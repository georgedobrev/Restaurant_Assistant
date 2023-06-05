package com.blankfactor.ra.controller;


import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.QrCode;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/{restaurantId}/tables")
@AllArgsConstructor
public class AppTableController {

    private final AppTableService appTableService;
    private final RestaurantService restaurantService;

    @PostMapping("/create")
    public ResponseEntity<List<AppTable>> createTable(@PathVariable("restaurantId") Integer restaurantId,
                                                    @RequestBody List<AppTable> appTables) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (restaurant == null) {
            return ResponseEntity.notFound().build();
        }
        List<QrCode> qrCodes = appTableService.createTablesForRestaurant(restaurant, appTables);

        HttpHeaders headers = new HttpHeaders();
        headers.add("custom-header", "value");
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(appTables);
    }

    @GetMapping("/{tableNumber}")
    public ResponseEntity<Optional<AppTable>> getTableByTableNumber(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) {
        Optional<AppTable> appTable = appTableService.getTableByTableNumber(restaurantId, tableNumber);
        return ResponseEntity.ok(appTable);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppTableDto>> getTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        List<AppTableDto> appTables = appTableService.getTablesByRestaurantId(restaurantId);
        return ResponseEntity.ok(appTables);
    }

    @PutMapping("/{tableNumber}")
    public ResponseEntity<AppTable> updateTableByTableNumber(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("tableNumber") Integer tableNumber, @RequestBody AppTable appTable) {
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

