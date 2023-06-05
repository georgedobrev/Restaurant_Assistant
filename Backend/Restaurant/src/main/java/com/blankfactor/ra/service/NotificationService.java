package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;

import java.util.List;

public interface NotificationService {
    //Notification getNotificationById(Integer notificationId) throws Exception;
    //List<Notification> getAllNotificationsByRestaurantId();
    //List<Notification> getAllNotificationsByTableId();
    Notification createNotification(NotificationDto notificationsDto);

    public Notification updateNotification(NotificationDto notificationDto, int notificationId);

    Notification deleteNotification(int notificationId);

    Notification deleteAllNotificationsByRestaurantId(int restaurantId);
}
