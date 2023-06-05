package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationsService {

    private final NotificationRepository notificationRepository;


    public void createNewNotification( Notification notification) {
        Optional<Notification> studentOptional = notificationRepository.findById(notification.getNotification_id());

        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("email taken");
        }
        notificationRepository.save(notification);
    }
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
//    public Notification getNotificationById(int notificationId)
//    {
//        return notificationRepository.getReferenceById(notificationId);
//    }
}
