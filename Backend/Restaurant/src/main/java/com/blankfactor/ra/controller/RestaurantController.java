package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantIds}")
    public ResponseEntity<List<Restaurant>> getRestaurantById(@PathVariable List<Integer> ids) {
        List<Restaurant> restaurants = restaurantService.getRestaurantsByIds(ids);

        if (!restaurants.isEmpty()) {
            return ResponseEntity.ok(restaurants);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable Integer restaurantId, @RequestBody Restaurant restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantById(restaurantId, restaurant);
        if (updatedRestaurant != null) {
            return ResponseEntity.ok(updatedRestaurant);
        }
        return ResponseEntity.notFound().build();
    }
}