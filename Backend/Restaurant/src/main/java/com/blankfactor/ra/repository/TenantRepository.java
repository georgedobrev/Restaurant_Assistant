package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}
