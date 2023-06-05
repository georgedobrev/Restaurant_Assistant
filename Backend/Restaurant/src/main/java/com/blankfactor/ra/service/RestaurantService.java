package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {

    Restaurant save(RestaurantDto restaurantDto);

    Restaurant createRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Optional<List<Restaurant>> getRestaurantsByIds(List<Integer> restaurantIds);

    Optional<Restaurant> updateRestaurantById(Integer restaurantId, Restaurant updatedRestaurant) throws Exception;

    Restaurant getRestaurantById(Integer restaurantId) throws Exception;
}