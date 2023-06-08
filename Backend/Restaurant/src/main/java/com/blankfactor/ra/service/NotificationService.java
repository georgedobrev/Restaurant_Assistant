package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;

import java.util.List;

public interface NotificationService {

    Notification createNotification(NotificationDto notificationDto);

    List<Notification> getAllNotificationsByRestaurantId(int restaurantId); //?

    List<Notification> getAllNotificationsByTableId(int tableId);
}
