package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.SysadminDto;
import com.blankfactor.ra.exceptions.custom.SysadminException;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.service.SysadminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sysadmin")
@AllArgsConstructor
@RestController
public class SysadminController {
    private final SysadminService sysadminService;

    @PostMapping()
    public ResponseEntity<Sysadmin> createSysadmin(@RequestBody SysadminDto sysadminDto) {
        var createdSysadmin = sysadminService.createSysadmin(sysadminDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSysadmin);
    }

    @GetMapping("/{sysadminId}")
    public ResponseEntity<Sysadmin> getSysadminById(@PathVariable("sysadminId") int sysadminId) {
        var sysadmin = sysadminService.getSysadminById(sysadminId);
        return ResponseEntity.ok().body(sysadmin);
    }

    @PutMapping("/{sysadminId}")
    public ResponseEntity<Sysadmin> updateSysadmin(@PathVariable("sysadminId") int sysadminId, @RequestBody SysadminDto sysadminDto) throws SysadminException {
        var sysadmin = sysadminService.updateSysadminById(sysadminId, sysadminDto);
        return ResponseEntity.ok(sysadmin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sysadmin>> getAllSysadminsByRestaurantId() {
        var sysadmins = sysadminService.getAllSysadmins();

        return ResponseEntity.ok(sysadmins);
    }

    @DeleteMapping("/{sysadminId}")
    public ResponseEntity<?> deleteSysadmin(@PathVariable("sysadminId") int sysadminId) {
        sysadminService.deleteSysadminById(sysadminId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAllSysadmins() {
        sysadminService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
