package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.TenantDto;
import com.blankfactor.ra.exceptions.custom.TenantException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.repository.TenantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;

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
    public Tenant updateTenant(int userId, TenantDto tenantDto) {

        AppUser appUserToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(""));

        Tenant tenantToUpdate = tenantRepository.findTenantByEmail(appUserToUpdate.getEmail())
                .orElseThrow(() -> new TenantException(""));

        tenantToUpdate.setEmail(tenantDto.getEmail());
        appUserToUpdate.setEmail(tenantDto.getEmail());

        return tenantRepository.save(tenantToUpdate);
    }
}