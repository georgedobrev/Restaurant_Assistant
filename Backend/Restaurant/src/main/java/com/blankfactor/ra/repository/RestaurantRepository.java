package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAllByDeletedIsFalse();

    Optional<Restaurant> findByIdAndDeletedIsFalse(Integer restaurantId);

    @Modifying
    @Query("UPDATE Restaurant r SET r.deleted = true WHERE r.id = :restaurantId")
    void softDeleteRestaurant(Integer restaurantId);
}