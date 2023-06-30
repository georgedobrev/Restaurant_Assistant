package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.service.TenantService;
import com.blankfactor.ra.service.UserService;
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

    @PostMapping("/create")
    public ResponseEntity<Tenant> createTenant(@Valid @RequestBody TenantDto tenantDto) {
        var createdTenant = tenantService.createTenant(tenantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @GetMapping("/get/{tenant_id}")
    public ResponseEntity<Tenant> getTenantById(@PathVariable("tenant_id") int tenantId) throws Exception {
        var tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        var restaurants = tenantService.getAllTenants();

        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/update/{tenant_id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("tenant_id") int tenantId) throws Exception {
        var tenant = tenantService.updateTenant(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @DeleteMapping("/delete/{tenant_id}")
    public ResponseEntity<?> deleteTenant(@PathVariable("tenant_id") int tenantId) {
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll() {
        tenantService.deleteAll();

        return ResponseEntity.ok().build();
    }
}
