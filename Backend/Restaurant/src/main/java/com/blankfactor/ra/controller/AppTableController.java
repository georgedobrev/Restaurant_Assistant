package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.AppTableService;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{restaurantId}/tables")
@AllArgsConstructor
public class AppTableController {

    private final AppTableService appTableService;
    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<List<AppTable>> createTable(@PathVariable("restaurantId") Integer restaurantId,
                                                      @RequestBody List<AppTable> appTables) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        appTableService.createTablesForRestaurant(restaurant, appTables);

        return ResponseEntity.status(HttpStatus.CREATED).body(appTables);
    }

    @GetMapping("/{tableNumber}")
    public ResponseEntity<AppTable> getTableByTableNumber(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) throws Exception {
        AppTable appTable = appTableService.getTableByTableNumber(restaurantId, tableNumber);

        return ResponseEntity.ok(appTable);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppTableDto>> getTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        List<AppTableDto> appTables = appTableService.getTablesByRestaurantId(restaurantId);

        return ResponseEntity.ok(appTables);
    }

    @PutMapping("/{tableNumber}")
    public ResponseEntity<AppTableDto> updateTableByTableNumber(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("tableNumber") Integer tableNumber, @RequestBody AppTableDto appTableDto) throws Exception {
        AppTableDto updatedTable = appTableService.updateTableByNumber(restaurantId, tableNumber, appTableDto);

        return ResponseEntity.ok(updatedTable);
    }

    @DeleteMapping("/{tableNumber}")
    public ResponseEntity<Boolean> removeTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) throws Exception {
        boolean removedTable = appTableService.removeTableByName(restaurantId, tableNumber);

        return ResponseEntity.ok(removedTable);
    }
}
