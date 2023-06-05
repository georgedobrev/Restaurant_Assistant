package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.NotificationsDto;
import com.blankfactor.ra.model.Notification;
import com.blankfactor.ra.repository.NotificationRepository;
import com.blankfactor.ra.service.impl.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
public class NotificationsController {

    private final NotificationRepository notificationRepository;
   // @Autowired
    private NotificationServiceImpl notificationService;

    public NotificationsController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/notification")
    public void createNotification(@RequestBody Notification notification)
    {
        notificationService.createNewNotification(notification);
        notificationRepository.save(notification);
    }


    @GetMapping("/notification/{id}")
    public Optional<Notification> findById(@PathVariable("notification_id") Integer notificationId)
    {
        //notificationService.getNotificationById(notificationId);

      // Optional<Notification> notificationsDtos = Optional.ofNullable(notificationService.getNotificationById(notificationId));
        return notificationRepository.findById(notificationId);
    }

//    @GetMapping("/notifications")
//    public ResponseEntity<List<Notification>> getAllNotifications(@RequestParam (required = false) Notification notification)
//    {
//        List<Notification> foundNotifications = new ArrayList<>();
//        notification = notificationService.getClass(notification);
//        return
//    }
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notificationsDtos = notificationService.getNotifications();
        return ResponseEntity.ok(notificationsDtos);
    }

//    @PostMapping("/notifications")
//    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification)
//    {
////
////        if (notification != null) {
////            return ResponseEntity.ok(notification);
////        } else {
////            return ResponseEntity.notFound().build();
////        }
//
//        notificationRepository.save(notification);
//        return new ResponseEntity<>(notification, HttpStatus.CREATED);
//
//    }

    @DeleteMapping(path = "{/notification/{id}")
    public void deleteNotification(@PathVariable("notification_id") int notificationId)
    {
        notificationService.deleteNotification(notificationId);

    }

    @DeleteMapping(path = "/notifications")
    public void deleteAllNotifications()
    {
        notificationService.deleteAllNotifications();
    }

//    @PutMapping(path = "{notificationId}")
//    public void updateStudent(@PathVariable("notificationId") int notificationId,
//                              @RequestParam(required = false) String message,
//                              @RequestParam(required = false) LocalDate time)
//    {
//        notificationService.updateNotification(notificationId, message, time);
//    }


}
