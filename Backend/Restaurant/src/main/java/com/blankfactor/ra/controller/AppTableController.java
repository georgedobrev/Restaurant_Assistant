package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.AppTableDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.service.AppTableService;
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
    public ResponseEntity<List<AppTable>> createTable(@PathVariable("restaurantId") Integer restaurantId,
                                                      @RequestBody List<AppTable> appTables) throws Exception {
        List<AppTable> createdAppTables = appTableService.createTablesForRestaurant(restaurantId, appTables);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppTables);
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
    public ResponseEntity removeTableByName(@PathVariable Integer restaurantId, @PathVariable Integer tableNumber) throws Exception {
        appTableService.removeTableByName(restaurantId, tableNumber);
        return ResponseEntity.ok().build();
    }
}
