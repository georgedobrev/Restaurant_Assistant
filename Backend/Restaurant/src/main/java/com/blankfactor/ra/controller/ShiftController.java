package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.exceptions.custom.ShiftException;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.service.ShiftService;
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
    public ResponseEntity<Shift> createShift(@RequestBody ShiftDto shiftDto) {
        Shift createdShift = shiftService.createShift(shiftDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShift);
    }

    @GetMapping("/getAll/{restaurantId}")
    public ResponseEntity<List<Shift>> getAllShifts(@PathVariable("restaurantId") Integer restaurantId) {
        List<Shift> allSections = shiftService.getAllShifts(restaurantId);
        return ResponseEntity.ok(allSections);
    }

    @PutMapping("/{shiftId}")
    public ResponseEntity<Shift> updateShift(@PathVariable("shiftId") Integer shiftId, @RequestBody ShiftDto shiftDto) throws ShiftException {
        Shift updatedShift = shiftService.updateShift(shiftId, shiftDto);
        return ResponseEntity.ok(updatedShift);
    }

    @DeleteMapping("/{shiftId}")
    public ResponseEntity<?> deleteShift(@PathVariable("shiftId") Integer shiftId) throws ShiftException {
        shiftService.deleteShift(shiftId);
        return ResponseEntity.ok().build();
    }
}
