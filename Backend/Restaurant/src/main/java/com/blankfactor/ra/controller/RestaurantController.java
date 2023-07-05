package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.CreateRestaurantDto;
import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurants")
@AllArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping()
    @Operation(summary = "Create restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto) {
        var createdRestaurant = restaurantService.createRestaurant(createRestaurantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @GetMapping()
    @Operation(summary = "Get all restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        var restaurants = restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/admin/{adminId}")
    @Operation(summary = "Get all restaurants by specific admin")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByAdmin(@PathVariable("adminId") Integer userId) {
        var restaurants = restaurantService.getAllRestaurantsByAdmin(userId);

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Get restaurant by id")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("restaurantId") Integer restaurantId) {
        var restaurant = restaurantService.getRestaurantById(restaurantId);

        return ResponseEntity.ok(restaurant);
    }

    @PutMapping("/{restaurantId}")
    @Operation(summary = "Update restaurant by id")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable("restaurantId") Integer restaurantId,
                                                           @RequestBody RestaurantDto restaurant) {
        var updatedRestaurant = restaurantService.updateRestaurantById(restaurantId, restaurant);

        return ResponseEntity.ok(updatedRestaurant);
    }
}