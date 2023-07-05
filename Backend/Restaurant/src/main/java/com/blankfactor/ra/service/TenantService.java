package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.model.Tenant;

import java.util.List;

public interface TenantService {
    Tenant createTenant(TenantDto tenantDto);

    Tenant getTenantById(Integer tenantId);

    List<Tenant> getAllTenants();

    Tenant updateTenant(Integer userId, TenantDto updateTenantDto);

    void deleteTenant(Integer tenantId);
}