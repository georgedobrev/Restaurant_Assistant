package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    @Qualifier
    private final NotificationRepository notificationRepository;

    @Override
    public Notification save(NotificationsDto notificationsDto) {

       Notification notification = new Notification();

       notification.setAppTable(null);
       notification.setTime(Instant.now());
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

    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public Optional<Notification> findNotificationById(int id) {
        notificationRepository.findById(id);
        return Optional.empty();
    }

}
