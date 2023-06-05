package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    //ResponseEntity<NotificationTable> createNewNotification(NotificationsDto notificationsDto);

    Notification save(NotificationsDto notificationsDto);

    // Notification save(NotificationsDto notificationsDto);
    void deleteNotification(int notificationId);
    void deleteAllNotifications();
    List<Notification> getNotifications();

    Optional<Notification> findNotificationById(int id);



}
