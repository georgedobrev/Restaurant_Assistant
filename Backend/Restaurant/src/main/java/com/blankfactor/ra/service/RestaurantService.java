package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.CreateRestaurantDto;
import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getAllRestaurantsByAdmin(int userId) throws RestaurantException;

    Restaurant getRestaurantById(Integer restaurantId) throws RestaurantException;

    Restaurant updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant);

    void deleteRestaurantById(Integer restaurantId);
}