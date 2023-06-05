package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}