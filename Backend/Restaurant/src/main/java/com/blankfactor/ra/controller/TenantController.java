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
    @Operation(summary = "Create tenant")
    public ResponseEntity<Tenant> createTenant(@Valid @RequestBody TenantDto tenantDto) {
        var createdTenant = tenantService.createTenant(tenantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @GetMapping("/{tenantId}")
    @Operation(summary = "Get tenant by id")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenantId") Integer tenantId) throws Exception {
        var tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping()
    @Operation(summary = "Get all tenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        var restaurants = tenantService.getAllTenants();

        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update tenant")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("userId") Integer userId,
                                               @RequestBody TenantDto tenantDto) {
        var tenant = tenantService.updateTenant(userId, tenantDto);

        return ResponseEntity.ok(tenant);
    }

    @DeleteMapping("/{tenantId}")
    @Operation(summary = "Delete tenant by id")
    public ResponseEntity<?> deleteTenant(@PathVariable("tenantId") Integer tenantId) {
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.ok().build();
    }
}