package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SectionDto;
import com.blankfactor.ra.model.Section;
import com.blankfactor.ra.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("section")
@AllArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Section> createSection(@PathVariable("restaurantId") Integer restaurantId, @RequestBody SectionDto sectionDto) throws Exception {
        Section createdSection = sectionService.createSection(restaurantId, sectionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSection);
    }

    @GetMapping("/getAll/{restaurantId}")
    public ResponseEntity<List<Section>> getAllSections(@PathVariable("restaurantId") Integer restaurantId) throws Exception {
        List<Section> allSections = sectionService.getAllSections(restaurantId);
        return ResponseEntity.ok(allSections);
    }
}
