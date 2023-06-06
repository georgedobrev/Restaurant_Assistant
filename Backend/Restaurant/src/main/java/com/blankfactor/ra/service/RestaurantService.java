package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(RestaurantDto restaurantDto);
}