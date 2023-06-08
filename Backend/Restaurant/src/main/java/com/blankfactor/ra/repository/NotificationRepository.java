package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findAllByAppTableIdIn(List<Integer> appTableIds);

    List<Notification> findAllByAppTableId(Integer appTableId);

    void deleteNotificationById (int notificationId);

    void deleteAllByAppTableId(int tableId);
}
