package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.dto.UpdateTenantDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.TenantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public Tenant createTenant(TenantDto tenantDto) {
        Tenant tenant = Tenant.builder()
                .email(tenantDto.getEmail())
                .build();

        AppUser appUser = AppUser.builder()
                .email(tenantDto.getEmail())
                .build();

        userRepository.save(appUser);
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant getTenantById(int tenantId) throws Exception {
        return tenantRepository.findById(tenantId).orElseThrow(() -> new Exception("Tenant " + tenantId + " not found."));
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant updateTenant(int tenantId, UpdateTenantDto updateTenantDto) throws Exception {
        Tenant tenantToUpdate = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new Exception("Tenant " + tenantId + " not found"));

        tenantToUpdate.setEmail(updateTenantDto.getEmail());

        return tenantRepository.save(tenantToUpdate);
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
