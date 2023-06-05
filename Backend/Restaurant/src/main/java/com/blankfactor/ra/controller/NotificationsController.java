package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@AllArgsConstructor
public class NotificationsController {

    private final NotificationService notificationService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Notification> getNotificationById(@PathVariable("id") Integer notificationId) throws Exception {
//        Notification notification = notificationService.getNotificationById(notificationId);
//
//        return ResponseEntity.ok(notification);
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<Notification>> getAllNotificationsByTableId(int tableId) {
//        List<Notification> notificationsDtos = notificationService.getAllNotificationsByTableId();
//
//        return ResponseEntity.ok(notificationsDtos);
//    }
//    @GetMapping("/all")
//    public ResponseEntity<List<Notification>> getAllNotificationsByRestaurantId(int restaurantId) {
//        List<Notification> notificationsDtos = notificationService.getAllNotificationsByRestaurantId();
//
//        return ResponseEntity.ok(notificationsDtos);
//    }

    @PostMapping("/create")
    public ResponseEntity<Notification> createNotification(@RequestBody NotificationDto notificationsDto) {
        Notification notification = notificationService.createNotification(notificationsDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(notification);
    }

    @PutMapping("{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId,
                       @RequestBody NotificationDto notificationDto) {
        Notification notification = notificationService.updateNotification(notificationDto, notificationId);

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteNotification (@PathVariable("id") int notificationId) {
        Notification notification = notificationService.deleteNotification(notificationId);

        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<Notification> deleteAllNotificationsByRestaurantId(int restaurantId) {
       Notification notification = notificationService.deleteAllNotificationsByRestaurantId(restaurantId);

        return ResponseEntity.ok(notification);
    }


}
