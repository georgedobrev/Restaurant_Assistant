package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmail(String email);

}
