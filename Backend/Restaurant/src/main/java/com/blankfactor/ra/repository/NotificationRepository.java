package com.blankfactor.ra.repository;


import com.blankfactor.ra.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    //void deleteAllByRestaurantId(int restaurantId);
//    List<Notification> findAllByRestaurantId();

    //List<Notification> findAllByTableId();
}
