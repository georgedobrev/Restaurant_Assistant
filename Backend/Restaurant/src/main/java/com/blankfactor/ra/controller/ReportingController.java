package com.blankfactor.ra.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/report")
@RestController
public class ReportingController {

    @PostMapping
    public ResponseEntity<?> createReport() {
        return null;
    }
}
