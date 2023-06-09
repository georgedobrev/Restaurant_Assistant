package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.repository.TenantRepository;
import com.blankfactor.ra.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    @Override
    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        List<Integer> tenantIds = new ArrayList<>();

        for(Tenant restaurant: tenants) {
            tenantIds.add(restaurant.getId());
        }
        return tenantRepository.findAllById(tenantIds);
    }

    @Override
    public Tenant createTenant(Tenant tenant) {
        Tenant tenant1 = new Tenant();

        tenant1.setEmail(tenant.getEmail());
        tenant1.setRestaurantId(tenant.getRestaurantId());
        tenant1.setName(tenant.getName());
        tenant1.setSurname(tenant.getSurname());
        tenant1.setBlacklisted(tenant.getBlacklisted());
        tenant1.setActive(tenant.getActive());
        tenant1.setCreatedAt(Instant.now());

        return tenantRepository.save(tenant1);
    }

    @Override
    public Tenant getTenantById(int tenantId) throws Exception {

        return tenantRepository.findById(tenantId).orElseThrow(() -> new Exception("Tenant not found."));
    }

    @Override
    public Tenant updateTenant(int tenantId) throws Exception {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(() -> new Exception("Tenant not found"));

        tenant.setActive(!tenant.getActive());
        //tenant.setActive(tenant.getActive());

        return tenantRepository.save(tenant);
    }

    @Override
    public void deleteTenant(int tenantId) {

        tenantRepository.deleteById(tenantId);
    }

    @Override
    public void deleteAll() {

        tenantRepository.deleteAll();
    }
}
