package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.dto.WaiterSectionDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.model.WaiterSection;
import com.blankfactor.ra.model.WaiterSectionPK;
import com.blankfactor.ra.service.SectionService;
import com.blankfactor.ra.service.WaiterSectionService;
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
    public ResponseEntity<Section> createSection(@RequestBody SectionDto sectionDto) throws Exception {
        Section createdSection = sectionService.createSection(sectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSection);
    }

    @GetMapping("/getAll/{restaurantId}")
    public ResponseEntity<List<Section>> getAllSections(@PathVariable("restaurantId") Integer restaurantId) throws Exception {
        List<Section> allSections = sectionService.getAllSections(restaurantId);
        return ResponseEntity.ok(allSections);
    }

    @PutMapping("/update/{restaurantId}")
    public ResponseEntity<Section> updateSection(@PathVariable("restaurantId") Integer restaurantId, SectionDto sectionDto) {
//        TODO how to find the existing section (findById)
//        Section updatedSection = sectionService.updateSection(restaurantId, sectionDto);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/addWaiter")
    public ResponseEntity<WaiterSection> assignWaiterToSection(WaiterSectionDto waiterSectionDto) {
        // TODO Null Null Null
        WaiterSection createdWaiterSection = waiterSectionService.createWaiterSection(waiterSectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWaiterSection);
    }
}
