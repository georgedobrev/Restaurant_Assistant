package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.dto.ShiftDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.Shift;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("shifts")
@AllArgsConstructor
public class ShiftController {
    private final ShiftService shiftService;

    @PostMapping()
    public ResponseEntity<Shift> createShift(@RequestBody ShiftDto shiftDto) throws Exception {
        Shift createdShift = shiftService.createShift(shiftDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShift);
    }

    @GetMapping("/getAll/{restaurantId}")
    public ResponseEntity<List<Shift>> getAllShifts(@PathVariable("restaurantId") Integer restaurantId) throws Exception {
        List<Shift> allSections = shiftService.getAllShifts(restaurantId);
        return ResponseEntity.ok(allSections);
    }
}
