package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .tablesCount(restaurantDto.getTablesCount())
                .address(restaurantDto.getAddress())
                .phoneNumber1(restaurantDto.getPhoneNumber1())
                .phoneNumber2(restaurantDto.getPhoneNumber2())
                .phoneNumber3(restaurantDto.getPhoneNumber3())
                .active(restaurantDto.getActive())
                .build();

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
    public Restaurant updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant) throws Exception {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setTablesCount(updatedRestaurant.getTablesCount());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setPhoneNumber1(updatedRestaurant.getPhoneNumber1());
        existingRestaurant.setPhoneNumber2(updatedRestaurant.getPhoneNumber2());
        existingRestaurant.setPhoneNumber3(updatedRestaurant.getPhoneNumber3());
        existingRestaurant.setActive(updatedRestaurant.getActive());

        return restaurantRepository.save(existingRestaurant);
    }

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));
    }
}