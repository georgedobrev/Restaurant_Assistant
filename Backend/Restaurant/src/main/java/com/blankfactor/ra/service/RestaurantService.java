package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.CreateRestaurantDto;
import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;

import java.util.List;

public interface RestaurantService {
    Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getAllRestaurantsByAdmin(Integer userId);

    Restaurant updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant);

    Restaurant getRestaurantById(Integer restaurantId);
}