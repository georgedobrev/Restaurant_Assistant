package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.VirtualTableDto;
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
                                                           @RequestBody VirtualTableDto virtualTableDto) {
        VirtualTable createdVirtualTable = virtualTableService.createVirtualTable(restaurantId, virtualTableDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVirtualTable);
    }

    @GetMapping()
    public ResponseEntity<Map<Integer, VirtualTable>> getAllVirtualTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        Map<Integer, VirtualTable> allVirtualTables = virtualTableService.getAllVirtualTablesByRestaurantId(restaurantId);
        return ResponseEntity.ok(allVirtualTables);
    }

    @PutMapping("/{virtualTableId}")
    public ResponseEntity<VirtualTable> updateVirtualTableByVirtualTableId(@PathVariable("restaurantId") Integer restaurantId,
                                                                           @PathVariable("virtualTableId") Integer virtualTableId,
                                                                           @RequestBody VirtualTable virtualTable) {
        VirtualTable updatedVirtualTable = virtualTableService.updateVirtualTableByVirtualTableId(restaurantId, virtualTableId, virtualTable);
        return ResponseEntity.ok(updatedVirtualTable);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteVirtualTable(@PathVariable("restaurantId") Integer restaurantId,
                                                @RequestBody VirtualTable virtualTable) {
        virtualTableService.deleteVirtualTable(restaurantId, virtualTable);
        return ResponseEntity.ok().build();
    }
}