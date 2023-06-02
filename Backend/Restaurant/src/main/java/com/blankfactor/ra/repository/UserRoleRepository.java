package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
}
