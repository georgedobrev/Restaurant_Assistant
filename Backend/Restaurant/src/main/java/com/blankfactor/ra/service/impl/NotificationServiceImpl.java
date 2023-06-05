package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.NotificationTable;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationsService {

    private final NotificationRepository notificationRepository;
    private final NotificationsService notificationsService;

    @Override
    public NotificationTable save(NotificationsDto notificationsDto) {

       NotificationTable notification = new NotificationTable();

       notification.setId((notificationsDto.getId()));
       notification.setAppTable(notificationsDto.getAppTable());
       notification.setTime(notificationsDto.getTime());
       notification.setMessage(notificationsDto.getMessage());
       notification.setApproved(notificationsDto.isApproved());

        return notificationRepository.save(notification);
    }

//    public void updateSNotification(int notificationId, String message, LocalDate time) {    }

    public void deleteNotification(int notificationId) {
        boolean exists = notificationRepository.existsById(notificationId);

        if(!exists)
        {
            throw new IllegalStateException("Notification with id " + notificationId + "does not exists.");
        }
        notificationRepository.deleteById(notificationId);
    }

    public void deleteAllNotifications()
    {
        notificationRepository.deleteAll();

    }

    public List<NotificationTable> getNotifications() {
        return notificationRepository.findAll();
    }
//    public Notification getNotificationById(int notificationId)
//    {
//        return notificationRepository.getReferenceById(notificationId);
//    }
}
