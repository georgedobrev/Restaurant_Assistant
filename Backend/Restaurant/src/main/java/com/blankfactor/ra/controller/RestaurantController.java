package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.service.RestaurantService;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(restaurantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/getAllByAdmin")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByAdmin() {
        List<Restaurant> restaurants =  restaurantService.getAllAdminRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantIds}")
    public ResponseEntity<List<Restaurant>> getRestaurantsById(@PathVariable("restaurantIds") List<Integer> restaurantIds) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByIds(restaurantIds);

        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable("restaurantId") Integer restaurantId, @RequestBody RestaurantDto restaurant) throws Exception {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantById(restaurantId, restaurant);

        return ResponseEntity.ok(updatedRestaurant);
    }
}