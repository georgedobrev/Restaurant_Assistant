package com.blankfactor.ra.service;

import com.blankfactor.ra.model.Tenant;

import java.util.List;

public interface TenantService {
    List<Tenant> getAllTenants();
    Tenant createTenant(Tenant tenant);

    Tenant getTenantById(int tenantId) throws Exception;
    Tenant updateTenant(int tenantId) throws Exception;

    void deleteTenant(int tenantId);

    void deleteAll();
}
