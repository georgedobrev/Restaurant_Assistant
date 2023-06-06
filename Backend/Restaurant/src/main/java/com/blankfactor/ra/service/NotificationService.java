package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    Notification createNotification(NotificationDto notificationDto);
    Notification getNotificationById(Integer notificationId) throws Exception;

    List<Notification> getAllNotificationsByTableId(Integer tableId);
    // List<Notification> getAllNotificationsByRestaurantId(int restaurantId);
    //    public Notification updateNotification(NotificationDto notificationDto, int notificationId);


    //Notification deleteNotification(int notificationId);

    // Notification deleteAllNotificationsByRestaurantId(int restaurantId);
}
