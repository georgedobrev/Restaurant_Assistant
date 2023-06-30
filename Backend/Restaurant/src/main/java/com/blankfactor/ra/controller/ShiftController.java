package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.service.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shift")
@AllArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @PostMapping()
    @Operation(summary = "Create shift for a specific restaurant")
    public ResponseEntity<Shift> createShift(@Valid @RequestBody ShiftDto shiftDto) {
        var createdShift = shiftService.createShift(shiftDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShift);
    }

    @GetMapping("/all/{restaurantId}")
    @Operation(summary = "Get all shifts for a specific restaurant")
    public ResponseEntity<List<Shift>> getAllShiftsByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        var allShifts = shiftService.getAllShiftsByRestaurantId(restaurantId);
        return ResponseEntity.ok(allShifts);
    }

    @PutMapping("/{shiftId}")
    @Operation(summary = "Update shift by id")
    public ResponseEntity<Shift> updateShiftById(@PathVariable("shiftId") Integer shiftId,
                                                 @Valid @RequestBody ShiftDto shiftDto) throws ShiftException {
        var updatedShift = shiftService.updateShiftById(shiftId, shiftDto);
        return ResponseEntity.ok(updatedShift);
    }

    @DeleteMapping("/{shiftId}")
    @Operation(summary = "Delete shift by id")
    public ResponseEntity<?> deleteShiftById(@PathVariable("shiftId") Integer shiftId) throws ShiftException {
        shiftService.deleteShiftById(shiftId);
        return ResponseEntity.ok().build();
    }
}
