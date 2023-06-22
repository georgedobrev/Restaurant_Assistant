package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.WaiterSection;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.WaiterSectionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/section")
@AllArgsConstructor
public class SectionController {
    private final SectionService sectionService;
    private final WaiterSectionService waiterSectionService;

    @PostMapping()
    @Operation(summary = "Create section for a specific restaurant")
    public ResponseEntity<Section> createSection(@Valid @RequestBody SectionDto sectionDto) {
        Section createdSection = sectionService.createSection(sectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSection);
    }

    @GetMapping("/getAll/{restaurantId}")
    @Operation(summary = "Get all sections from a specific restaurant")
    public ResponseEntity<List<Section>> getAllSections(@PathVariable("restaurantId") Integer restaurantId) {
        List<Section> allSections = sectionService.getAllSections(restaurantId);
        return ResponseEntity.ok(allSections);
    }

    @PostMapping("/addWaiter")
    @Operation(summary = "Assign waiter to a section with a shift")
    public ResponseEntity<WaiterSection> assignWaiterToSection(@Valid @RequestBody WaiterSectionDto waiterSectionDto) {
        // TODO Null Null Null
        WaiterSection createdWaiterSection = waiterSectionService.createWaiterSection(waiterSectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWaiterSection);
    }
}
