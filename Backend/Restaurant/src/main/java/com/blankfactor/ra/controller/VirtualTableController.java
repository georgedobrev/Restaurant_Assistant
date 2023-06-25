package com.blankfactor.ra.controller;

import com.blankfactor.ra.model.VirtualTable;
import com.blankfactor.ra.service.VirtualTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/virtual/{restaurantId}")
@AllArgsConstructor
public class VirtualTableController {
    private final VirtualTableService virtualTableService;

    @PostMapping()
    public ResponseEntity<VirtualTable> createVirtualTable(@PathVariable("restaurantId") Integer restaurantId,
                                                           @RequestBody VirtualTable virtualTable) {
        VirtualTable createdVirtualTable = virtualTableService.createVirtualTable(restaurantId, virtualTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(virtualTable);
    }

    @GetMapping()
    public ResponseEntity<VirtualTable> getVirtualTableByTableNumbersAndRestaurantId(@PathVariable("restaurantId") Integer restaurantId,
                                                                                     @RequestBody String tableIds) {
        VirtualTable virtualTable = virtualTableService.getVirtualTableByTableNumbersAndRestaurantId(tableIds, restaurantId);
        return ResponseEntity.ok(virtualTable);
    }

    @GetMapping("/all")
    public ResponseEntity<Map<Integer, VirtualTable>> getAllVirtualTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        Map<Integer, VirtualTable> allVirtualTables = virtualTableService.getAllVirtualTablesByRestaurantId(restaurantId);
        return ResponseEntity.ok(allVirtualTables);
    }

    @PutMapping("/{tableNumbers}")
    public ResponseEntity<VirtualTable> updateVirtualTableByTableNumbers(@PathVariable("restaurantId") Integer restaurantId,
                                                                         @PathVariable("tableNumbers") String tableNumbers,
                                                                         @RequestBody VirtualTable virtualTable) {
        VirtualTable updatedVirtualTable = virtualTableService.updateVirtualTableByTableNumbers(restaurantId, tableNumbers, virtualTable);
        return ResponseEntity.ok(updatedVirtualTable);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteVirtualTable(@PathVariable("restaurantId") Integer restaurantId,
                                                @RequestBody VirtualTable virtualTable) {
        virtualTableService.deleteVirtualTable(restaurantId, virtualTable);
        return ResponseEntity.ok().build();
    }
}