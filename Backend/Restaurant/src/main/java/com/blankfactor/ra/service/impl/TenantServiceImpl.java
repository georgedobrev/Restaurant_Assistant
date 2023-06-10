package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.TenantDto;
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
    public Tenant createTenant(TenantDto tenantDto) {
        Tenant tenant = new Tenant();

        tenant.setEmail(tenantDto.getEmail());
        tenant.setRestaurantId(tenantDto.getRestaurantId());
        tenant.setName(tenantDto.getName());
        tenant.setSurname(tenantDto.getSurname());
        tenant.setBlacklisted(tenantDto.isBlacklisted());
        tenant.setActive(tenantDto.isActive());
        tenant.setCreatedAt(Instant.now());

        return tenantRepository.save(tenant);
    }

    @Override
    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = tenantRepository.findAll();
        List<Integer> tenantIds = new ArrayList<>();

        for (Tenant restaurant : tenants) {
            tenantIds.add(restaurant.getId());
        }
        return tenantRepository.findAllById(tenantIds);
    }

    @Override
    public Tenant getTenantById(int tenantId) throws Exception {
        return tenantRepository.findById(tenantId).orElseThrow(() -> new Exception("Tenant " + tenantId + " not found."));
    }

    @Override
    public Tenant updateTenant(int tenantId) throws Exception {
        Tenant tenant = tenantRepository.findById(tenantId).orElseThrow(() -> new Exception("Tenant " + tenantId + " not found"));

        tenant.setActive(!tenant.getActive());
        tenant.setBlacklisted(!tenant.getBlacklisted());
        tenant.setEmail(tenant.getEmail());

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
