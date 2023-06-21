package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.ReportingDto;
import com.blankfactor.ra.model.Reporting;
import com.blankfactor.ra.service.ReportingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportingController {
    private final ReportingService reportingService;

    @PostMapping
    public ResponseEntity<Reporting> createReport(@Valid @RequestBody ReportingDto reportingDto) {
        Reporting report = reportingService.createReport(reportingDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }
}
