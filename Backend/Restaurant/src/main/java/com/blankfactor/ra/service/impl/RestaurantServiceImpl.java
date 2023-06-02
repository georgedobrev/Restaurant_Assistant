package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.AppTableRepository;
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
    private final AppTableRepository appTableRepository;

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
    public List<Restaurant> getRestaurantsByIds(List<Integer> restaurantIds) {
        return restaurantRepository.findAllById(restaurantIds);
    }

    @Override
    public Restaurant updateRestaurantById(Integer restaurantId, Restaurant updatedRestaurant) {
        Optional<Restaurant> existingRestaurant = restaurantRepository.findById(restaurantId);
        if (existingRestaurant.isPresent()) {
            Restaurant restaurantToUpdate = existingRestaurant.get();

            restaurantToUpdate.setName(updatedRestaurant.getName());
            restaurantToUpdate.setActive(updatedRestaurant.getActive());
            restaurantToUpdate.setAddress(updatedRestaurant.getAddress());
            restaurantToUpdate.setPhoneNumber1(updatedRestaurant.getPhoneNumber1());
            restaurantToUpdate.setPhoneNumber2(updatedRestaurant.getPhoneNumber2());
            restaurantToUpdate.setPhoneNumber3(updatedRestaurant.getPhoneNumber3());
            restaurantToUpdate.setTablesCount(updatedRestaurant.getTablesCount());

            return restaurantRepository.save(restaurantToUpdate);
        }
        return null;
    }

    @Override
    public Restaurant getRestaurantsById(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }
}