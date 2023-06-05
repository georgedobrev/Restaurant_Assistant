package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant createdRestaurant = restaurantService.save(restaurantDto);

        return ResponseEntity.ok(createdRestaurant);
    }
}