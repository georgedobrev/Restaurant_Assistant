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
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {
    private final TenantService tenantService;

    @PostMapping("")
    public ResponseEntity<Tenant> createTenant(@Valid @RequestBody TenantDto tenantDto) {
        Tenant createdTenant = tenantService.createTenant(tenantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @GetMapping("/{tenant_id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenant_id") int tenantId) throws Exception {
        Tenant tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> restaurants = tenantService.getAllTenants();

        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{user_id}")
    @Operation(summary = "Update tenant")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("user_id") int userId,
                                               @RequestBody TenantDto tenantDto) {
        Tenant tenant = tenantService.updateTenant(userId, tenantDto);

        return ResponseEntity.ok(tenant);
    }

    @DeleteMapping("/{tenant_id}")
    public ResponseEntity<?> deleteTenant(@PathVariable("tenant_id") int tenantId) {
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        tenantService.deleteAll();

        return ResponseEntity.ok().build();
    }
}
