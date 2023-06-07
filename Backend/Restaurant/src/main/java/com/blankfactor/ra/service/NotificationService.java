package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationService {

    Notification createNotification(NotificationDto notificationDto);
}
