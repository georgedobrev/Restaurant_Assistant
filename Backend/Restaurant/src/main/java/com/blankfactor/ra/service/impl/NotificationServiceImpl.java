package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    //  private final AppTableRepository appTableRepository;
    @Override
    public Notification createNotification(NotificationDto notificationDto)
    {
        Notification notification = new Notification();

        notification.setAppTable(notificationDto.getAppTable());
        notification.setAppUser(notificationDto.getAppUser());
        notification.setRequestType(notificationDto.getRequestType());
        notification.setMessage(notificationDto.getMessage());
        notification.setApproved(notificationDto.isApproved());
        notification.setCreatedAt(OffsetDateTime.now());

        return notificationRepository.save(notification);
    }


}
