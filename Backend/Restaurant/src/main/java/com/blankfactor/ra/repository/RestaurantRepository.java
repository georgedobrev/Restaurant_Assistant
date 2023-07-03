package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Query("UPDATE Restaurant r SET r.deleted = true WHERE r.id = :restaurantId")
    void softDeleteRestaurant(Integer restaurantId);
}