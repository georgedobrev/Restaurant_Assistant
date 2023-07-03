package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.service.AppTableService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables/{restaurantId}")
@AllArgsConstructor
public class AppTableController {
    private final AppTableService appTableService;

    @PostMapping()
    @Operation(summary = "Create tables for existing restaurant")
    public ResponseEntity<List<AppTable>> createTablesForRestaurant(@PathVariable("restaurantId") Integer restaurantId,
                                                                    @RequestBody List<AppTableDto> appTablesDto) {
        var createdAppTables = appTableService.createTablesForRestaurant(restaurantId, appTablesDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppTables);
    }

    @GetMapping("/{tableNumber}")
    @Operation(summary = "Get table by table number")
    public ResponseEntity<AppTable> getTableByTableNumber(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) {
        var appTable = appTableService.getTableByTableNumber(restaurantId, tableNumber);

        return ResponseEntity.ok(appTable);
    }

    @GetMapping()
    @Operation(summary = "Get all tables for a specific restaurant")
    public ResponseEntity<List<AppTable>> getTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        var appTables = appTableService.getTablesByRestaurantId(restaurantId);

        return ResponseEntity.ok(appTables);
    }

    @PutMapping("/{tableNumber}")
    @Operation(summary = "Update table by table number")
    public ResponseEntity<AppTable> updateTableByTableNumber(@PathVariable("restaurantId") Integer restaurantId, @PathVariable("tableNumber") Integer tableNumber, @RequestBody AppTableDto appTableDto) {
        var updatedTable = appTableService.updateTableByNumber(restaurantId, tableNumber, appTableDto);

        return ResponseEntity.ok(updatedTable);
    }

    @DeleteMapping("/{tableNumber}")
    @Operation(summary = "Delete table by table number")
    public ResponseEntity<?> removeTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) {
        appTableService.removeTableByName(restaurantId, tableNumber);

        return ResponseEntity.ok().build();
    }
}