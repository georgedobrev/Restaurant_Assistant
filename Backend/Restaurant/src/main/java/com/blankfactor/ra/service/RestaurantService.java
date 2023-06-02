package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.Restaurant;
import com.google.zxing.qrcode.encoder.QRCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RestaurantService {

    Restaurant save(RestaurantDto restaurantDto);
    Restaurant createRestaurant(Restaurant restaurant);
    List<Restaurant> getAllRestaurants();
    List<Restaurant> getRestaurantsByIds(List<Integer> restaurantIds);
    Restaurant updateRestaurantById(Integer restaurantId, Restaurant updatedRestaurant);
    Restaurant getRestaurantsById(Integer restaurantId);

}