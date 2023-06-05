package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.NotificationTable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationsService {
    //ResponseEntity<NotificationTable> createNewNotification(NotificationsDto notificationsDto);

    NotificationTable save(NotificationsDto notificationsDto);

    // Notification save(NotificationsDto notificationsDto);
    void deleteNotification(int notificationId);
    void deleteAllNotifications();
    List<NotificationTable> getNotifications();



}
