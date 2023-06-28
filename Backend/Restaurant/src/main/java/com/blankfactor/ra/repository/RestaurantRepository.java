package com.blankfactor.ra.repository;

import com.blankfactor.ra.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<String> findAllPhoneNumbersById(int restaurantId);
}