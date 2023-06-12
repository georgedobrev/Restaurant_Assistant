package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Integer> {
}
