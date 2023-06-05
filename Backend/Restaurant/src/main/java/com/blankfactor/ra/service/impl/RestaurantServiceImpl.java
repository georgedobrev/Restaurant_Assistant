package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant save(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();

        restaurant.setName(restaurantDto.getName());
        restaurant.setTablesCount(restaurantDto.getTablesCount());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setPhoneNumber1(restaurantDto.getPhoneNumber1());
        restaurant.setPhoneNumber2(restaurantDto.getPhoneNumber2());
        restaurant.setPhoneNumber3(restaurantDto.getPhoneNumber3());
        restaurant.setActive(restaurantDto.getActive());

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Optional<List<Restaurant>> getRestaurantsByIds(List<Integer> restaurantIds) {
        return Optional.of(restaurantRepository.findAllById(restaurantIds));
    }

    @Override
    public Optional<Restaurant> updateRestaurantById(Integer restaurantId, Restaurant updatedRestaurant) throws Exception {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setActive(updatedRestaurant.getActive());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setPhoneNumber1(updatedRestaurant.getPhoneNumber1());
        existingRestaurant.setPhoneNumber2(updatedRestaurant.getPhoneNumber2());
        existingRestaurant.setPhoneNumber3(updatedRestaurant.getPhoneNumber3());
        existingRestaurant.setTablesCount(updatedRestaurant.getTablesCount());

        return Optional.of(restaurantRepository.save(existingRestaurant));
    }

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);
    }
}