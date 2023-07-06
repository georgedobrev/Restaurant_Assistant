package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmail(String email);

    Optional<AppUser> findAppUserByEmailAndDeletedIsFalse(String email);

    Optional<AppUser> findAppUserByEmailAndDeletedIsTrue(String email);

    Optional<AppUser> findAppUserByIdAndDeletedIsFalse(Integer id);

    @Modifying
    @Query("UPDATE AppUser u SET u.deleted = true WHERE u.id = :userId")
    void softDeleteUser(Integer userId);

    @Modifying
    @Query("UPDATE AppUser u SET u.deleted = false WHERE u.id = :userId")
    void softUpdateUser(Integer userId);
}