package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTableRepository extends JpaRepository<UserTable, Integer> {
    Optional<UserTable> findByAppUserIdAndAppTableId(Integer appUserId, AppTable appTableId);
}
