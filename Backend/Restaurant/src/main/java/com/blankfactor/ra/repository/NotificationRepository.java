package com.blankfactor.ra.repository;


import com.blankfactor.ra.dto.NotificationDto;
import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Notification findNotificationById(Integer id);


//    @Query("SELECT restaurant_id from restaurant")
    ////    List<Notification> find(int id);
//ResponseEntity<Notification> updateNotification(@PathVariable("id") int notificationId,
//                                                @RequestBody NotificationDto notificationDto);
    List<Notification> findAllByAppTableId(Integer appTableId);
    //Notification deleteNotification(int notificationId);
   // List<Notification> findAllByRestaurantId(Integer restaurantId);
    //Notification deleteAllByRestaurantId(int restaurantId);
}
