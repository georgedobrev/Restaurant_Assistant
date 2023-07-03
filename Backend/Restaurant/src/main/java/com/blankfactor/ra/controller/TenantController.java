package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.service.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tenants")
@AllArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping("")
    public ResponseEntity<Tenant> createTenant(@Valid @RequestBody TenantDto tenantDto) {
        var createdTenant = tenantService.createTenant(tenantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenantId") int tenantId) throws Exception {
        var tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping()
    public ResponseEntity<List<Tenant>> getAllTenants() {
        var restaurants = tenantService.getAllTenants();

        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update tenant")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("userId") int userId,
                                               @RequestBody TenantDto tenantDto) {
        var tenant = tenantService.updateTenant(userId, tenantDto);

        return ResponseEntity.ok(tenant);
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<?> deleteTenant(@PathVariable("tenantId") int tenantId) {
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteAll() {
        tenantService.deleteAll();

        return ResponseEntity.ok().build();
    }
}