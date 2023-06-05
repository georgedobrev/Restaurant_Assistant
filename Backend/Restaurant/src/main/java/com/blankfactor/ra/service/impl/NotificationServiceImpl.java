package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

//    @Override
//    public Notification getNotificationById(Integer notificationId) throws Exception {
//        return notificationRepository.findById(notificationId)
//                .orElseThrow(() -> new Exception ("Can not find this notification."));
//    }

//    @Override
//    public List<Notification> getAllNotificationsByRestaurantId() {
//        return notificationRepository.findAllByRestaurantId();
//    }

//    @Override
//    public List<Notification> getAllNotificationsByTableId() {
//        return notificationRepository.findAllByTableId();
//    }

    @Override
    public Notification createNotification(NotificationDto notificationsDto) {

        Notification notification = new Notification();

        notification.setAppTable(null);
        notification.setCreatedAt(Instant.now());
        notification.setMessage(notificationsDto.getMessage());
        notification.setApproved(notificationsDto.isApproved());

        return notificationRepository.save(notification);
    }

    public Notification updateNotification(NotificationDto notificationDto, int notificationId) {

        return null;
    }

    public Notification deleteNotification(int notificationId) {

        boolean exists = notificationRepository.existsById(notificationId);

        if (!exists) {
            throw new IllegalStateException("Notification with id " + notificationId + "does not exists.");
        }
        notificationRepository.deleteById(notificationId);
        return null;
    }

    @Override
    public Notification deleteAllNotificationsByRestaurantId(int restaurantId) {
        return null;
    }

//    public Notification deleteAllNotificationsByRestaurantId(int restaurantId) {
//        notificationRepository.deleteAllByRestaurantId(restaurantId);
//        return null;
//    }



}
