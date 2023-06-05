package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.NotificationTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationTable, Integer> {

}
