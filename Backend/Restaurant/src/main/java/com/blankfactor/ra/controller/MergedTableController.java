package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.MergedTableDto;
import com.blankfactor.ra.model.MergedTable;
import com.blankfactor.ra.service.MergedTableService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/mergedTable/{restaurantId}")
@AllArgsConstructor
public class MergedTableController {
    private final MergedTableService mergedTableService;

    @PostMapping()
    public ResponseEntity<MergedTable> createMergedTable(@PathVariable("restaurantId") Integer restaurantId,
                                                         @RequestBody MergedTableDto mergedTableDto) {
        MergedTable createdMergedTable = mergedTableService.createMergedTable(restaurantId, mergedTableDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdMergedTable);
    }

    @GetMapping()
    public ResponseEntity<Map<Integer, MergedTable>> getAllMergedTablesByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        Map<Integer, MergedTable> allMergedTables = mergedTableService.getAllMergedTablesByRestaurantId(restaurantId);

        return ResponseEntity.ok(allMergedTables);
    }

    @PutMapping("/{mergedTableId}")
    public ResponseEntity<MergedTable> updateMergedTableByMergedTableId(@PathVariable("restaurantId") Integer restaurantId,
                                                                        @PathVariable("mergedTableId") Integer mergedTableId,
                                                                        @RequestBody MergedTable mergedTable) {
        MergedTable updatedMergedTable = mergedTableService.updateMergedTableByMergedTableId(restaurantId, mergedTableId, mergedTable);

        return ResponseEntity.ok(updatedMergedTable);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteMergedTable(@PathVariable("restaurantId") Integer restaurantId,
                                               @RequestBody MergedTable mergedTable) {
        mergedTableService.deleteMergedTable(restaurantId, mergedTable);

        return ResponseEntity.ok().build();
    }
}