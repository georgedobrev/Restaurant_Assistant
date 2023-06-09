package com.blankfactor.ra.controller;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.service.TenantService;
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
    public ResponseEntity<Tenant> createTenant(@RequestBody Tenant tenant) {
        Tenant createdTenant = tenantService.createTenant(tenant);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTenant);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Tenant> updateTenant(@PathVariable("id") int tenantId) throws Exception {
        Tenant tenant = tenantService.updateTenant(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable("id") int tenantId) throws Exception {
        Tenant tenant = tenantService.getTenantById(tenantId);

        return ResponseEntity.ok(tenant);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> restaurants = tenantService.getAllTenants();

        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTenant(@PathVariable("id") int tenantId) {
        tenantService.deleteTenant(tenantId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity deleteAll() {
        tenantService.deleteAll();

        return ResponseEntity.ok().build();
    }


}
