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
    public ResponseEntity<VirtualTable> createVirtualTable(@PathVariable("restaurantId") Integer restaurantId, @RequestBody VirtualTable virtualTable) {
        VirtualTable createdVirtualTable = virtualTableService.createVirtualTable(restaurantId, virtualTable);
        return ResponseEntity.status(HttpStatus.CREATED).body(virtualTable);
    }

    @GetMapping("/get")
    public ResponseEntity<VirtualTable> getVirtualTable(@PathVariable("restaurantId") Integer restaurantId, @RequestBody String tableIds) throws Exception {
        VirtualTable virtualTable = virtualTableService.getVirtualTableByTableIdsAndRestaurantId(tableIds, restaurantId);
        return ResponseEntity.ok(virtualTable);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Map<Integer, VirtualTable>> getAllVirtualTables(@PathVariable("restaurantId") Integer restaurantId) {
        Map<Integer, VirtualTable> allVirtualTables = virtualTableService.getAllVirtualTablesByRestaurantId(restaurantId);
        return ResponseEntity.ok(allVirtualTables);
    }

    @PutMapping("/{tableNumbers}")
    public ResponseEntity<VirtualTable> updateVirtualTable(@PathVariable("restaurantId") Integer restaurantId ,@PathVariable("tableNumbers") String tableNumbers, @RequestBody VirtualTable virtualTable) throws Exception {
        VirtualTable updatedVirtualTable = virtualTableService.updateVirtualTableByTableNumbers(restaurantId, tableNumbers, virtualTable);
        return ResponseEntity.ok(updatedVirtualTable);
    }
}
