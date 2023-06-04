package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationsService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(NotificationsDto notificationsDto ) {
       Notification notification = new Notification();

       notification.setNotification_id(notificationsDto.getNotification_id());
       notification.setApp_table_id(notificationsDto.getTable_id());
       notification.setTime(notificationsDto.getTime());
       notification.setMessage(notificationsDto.getMessage());
       notification.setApproved(notificationsDto.isApproved());

        return notificationRepository.save(notification);
    }
}
