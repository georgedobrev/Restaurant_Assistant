package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.model.Tenant;

import java.util.List;

public interface TenantService {
    Tenant createTenant(TenantDto tenantDto);

    Tenant getTenantById(int tenantId) throws Exception;

    List<Tenant> getAllTenants();

    Tenant updateTenant(int tenantId) throws Exception;

    void deleteTenant(int tenantId);

    void deleteAll();
}
