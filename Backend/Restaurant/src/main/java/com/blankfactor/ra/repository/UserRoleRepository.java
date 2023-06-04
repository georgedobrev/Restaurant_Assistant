package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.model.UserRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePK> {
    List<UserRole> findByAppUser(AppUser appUser);
}
