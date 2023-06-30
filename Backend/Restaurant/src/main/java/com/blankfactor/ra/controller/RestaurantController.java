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
@RequestMapping("/restaurant")
@AllArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping()
    @Operation(summary = "Create restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantDto createRestaurantDto) {
        Restaurant createdRestaurant = restaurantService.createRestaurant(createRestaurantDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all restaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/allByAdmin/{admin_id}")
    @Operation(summary = "Get all restaurants by specific admin")
    public ResponseEntity<List<Restaurant>> getAllRestaurantsByAdmin(@PathVariable("admin_id") int userId) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurantsByAdmin(userId);

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Get restaurant by id")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable("restaurantId") Integer restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        return ResponseEntity.ok(restaurant);
    }
    @PutMapping("/{restaurantId}")
    @Operation(summary = "Update restaurant by id")
    public ResponseEntity<Restaurant> updateRestaurantById(@PathVariable("restaurantId") Integer restaurantId, @RequestBody RestaurantDto restaurant) {
        Restaurant updatedRestaurant = restaurantService.updateRestaurantById(restaurantId, restaurant);

        return ResponseEntity.ok(updatedRestaurant);
    }
}