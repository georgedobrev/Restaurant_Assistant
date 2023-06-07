package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Override
    public Restaurant createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();

        modelMapper.map(restaurantDto, restaurant);

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
    public RestaurantDto updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant) throws Exception {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);

        modelMapper.map(updatedRestaurant, existingRestaurant);

        return modelMapper.map(restaurantRepository.save(existingRestaurant), RestaurantDto.class);
    }

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) throws Exception {
        return restaurantRepository.findById(restaurantId).orElseThrow(Exception::new);
    }
}