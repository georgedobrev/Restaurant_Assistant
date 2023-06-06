package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(NotificationDto notificationDto) {

        Notification notification = new Notification();

        notification.setAppTable(notificationDto.getAppTable());
        notification.setAppUser(notificationDto.getAppUser());
        notification.setRequestType(notificationDto.getRequestType());
        notification.setRequestType(notificationDto.getRequestType());
        notification.setMessage(notificationDto.getMessage());
        notification.setApproved(notificationDto.isApproved());
        notification.setCreatedAt(Instant.now());

        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotificationById(Integer notificationId) throws Exception {
        return notificationRepository.findNotificationById(notificationId);
    }

    @Override
    public List<Notification> getAllNotificationsByTableId(Integer tableId) {
        return notificationRepository.findAllByAppTableId(tableId);
    }

//    @Override
//    public List<Notification> getAllNotificationsByRestaurantId(int restaurantId) {
//        List<Notification> notifications = notificationRepository.findAllByRestaurantId(restaurantId);
//
//       // AppTable appTable = notifications.g
//        return notifications;
//    }

//    public Notification updateNotification(NotificationDto notificationDto, int notificationId) {
//
//        Notification existingNotification = notificationRepository.findById(notificationId)
//                .orElseThrow(() -> new NoSuchElementException("Notification not found"));
//
//        // Update the existing notification with the new values from the DTO
//        notificationDto.setMessage(notificationDto.getMessage());
//        notificationDto.setApproved(notificationDto.isApproved());
//        notificationDto.setAppUser(notificationDto.getAppUser());
//        notificationDto.setAppTable(notificationDto.getAppTable());
//
//        // Save the updated notification in the database
//        Notification updatedNotification = notificationRepository.save(notificationDto);
//
//        return updatedNotification;
//    }

//    @Override
//    public Notification deleteNotification(int notificationId) {
//
//        boolean exists = notificationRepository.existsById(notificationId);
//
//        if (!exists) {
//            throw new IllegalStateException("Notification with id " + notificationId + "does not exists.");
//        }
//        return notificationRepository.deleteNotification(notificationId);
//
//
//    }

//    @Override
//    public Notification deleteAllNotificationsByRestaurantId(int restaurantId) {
//        return notificationRepository.deleteAllByRestaurantId(restaurantId);
//    }

}
