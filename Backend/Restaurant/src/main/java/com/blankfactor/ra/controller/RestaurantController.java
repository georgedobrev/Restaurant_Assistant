package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant createdRestaurant = restaurantService.save(restaurantDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdRestaurant);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantIds}")
    public ResponseEntity<Optional<List<Restaurant>>> getRestaurantsById(@PathVariable("restaurantIds") List<Integer> restaurantIds) {
        Optional<List<Restaurant>> restaurants = restaurantService.getRestaurantsByIds(restaurantIds);
        return ResponseEntity.ok(restaurants);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Optional<Restaurant>> updateRestaurantById(@PathVariable("restaurantId") Integer restaurantId, @RequestBody Restaurant restaurant) throws Exception {
        Optional<Restaurant> updatedRestaurant = restaurantService.updateRestaurantById(restaurantId, restaurant);
        return ResponseEntity.ok(updatedRestaurant);
    }
}