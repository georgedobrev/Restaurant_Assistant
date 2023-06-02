package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
}
