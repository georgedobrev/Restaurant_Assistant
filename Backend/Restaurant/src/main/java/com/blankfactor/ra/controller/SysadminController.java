package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SysadminDto;
import com.blankfactor.ra.exceptions.custom.SysadminException;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.service.SysadminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sysadmins")
@AllArgsConstructor
@RestController
public class SysadminController {
    private final SysadminService sysadminService;

    @PostMapping()
    @Operation(summary = "Create sysadmin")
    public ResponseEntity<Sysadmin> createSysadmin(@RequestBody SysadminDto sysadminDto) {
        var createdSysadmin = sysadminService.createSysadmin(sysadminDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSysadmin);
    }

    @GetMapping("/{sysadminId}")
    @Operation(summary = "Get sysadmin by id")
    public ResponseEntity<Sysadmin> getSysadminById(@PathVariable("sysadminId") int sysadminId) {
        var sysadmin = sysadminService.getSysadminById(sysadminId);
        return ResponseEntity.ok().body(sysadmin);
    }

    @GetMapping()
    @Operation(summary = "Get all sysadmins")
    public ResponseEntity<List<Sysadmin>> getAllSysadmins() {
        var sysadmins = sysadminService.getAllSysadmins();

        return ResponseEntity.ok(sysadmins);
    }

    @PutMapping("/{sysadminId}")
    @Operation(summary = "Update sysadmin")
    public ResponseEntity<Sysadmin> updateSysadmin(@PathVariable("sysadminId") Integer sysadminId, @RequestBody SysadminDto sysadminDto) throws SysadminException {
        var sysadmin = sysadminService.updateSysadminById(sysadminId, sysadminDto);
        return ResponseEntity.ok(sysadmin);
    }
}