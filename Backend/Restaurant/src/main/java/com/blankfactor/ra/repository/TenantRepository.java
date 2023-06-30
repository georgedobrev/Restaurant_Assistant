package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findTenantByEmail(String email);
}
