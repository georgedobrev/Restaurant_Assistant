package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByAppTableIdIn(List<Integer> appTableIds);

    List<Notification> findAllByAppTableId(Integer appTableId);

    void deleteAllByAppTableId(int tableId);

    @Query("SELECT ut.waiter FROM UserTable ut WHERE ut.appTableId.id = :tableId")
    List<AppUser> findWaiterByTableId(@Param("tableId") int tableId);
}