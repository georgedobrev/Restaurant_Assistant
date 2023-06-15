package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
    void deleteByAppUser(AppUser appUser);
}
