package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.CreateRestaurantDto;
import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;


    //TODO check if the userId matches for tenant
    @Override
    public Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        RestaurantDto restaurantDto = createRestaurantDto.getRestaurantDto();

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                //.tablesCount(restaurantDto.getTablesCount())
                .address(restaurantDto.getAddress())
                .phoneNumber1(restaurantDto.getPhoneNumber1())
                .phoneNumber2(restaurantDto.getPhoneNumber2())
                .phoneNumber3(restaurantDto.getPhoneNumber3())
                .build();

        Restaurant restaurant1 = restaurantRepository.save(restaurant);

        AppUser appUser = userRepository.findById(createRestaurantDto.getUserId())
                .orElseThrow(() -> new UserException(""));

        UserRole userRole = UserRole.builder()
                .appUser(appUser)
                .restaurant(restaurant1)
                .roleType(RoleType.ADMIN)
                .build();
        userRoleRepository.save(userRole);

        return restaurant1;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));
    }

    @Override
    public List<Restaurant> getAllRestaurantsByAdmin(int userId) {
        List<UserRole> userRoles = userRoleRepository.findByAppUserIdAndRoleType(userId, RoleType.ADMIN);
        List<Restaurant> userRestaurant = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            userRestaurant.add(userRole.getRestaurant());
        }

        return userRestaurant;
    }

    @Override
    public List<String> getAllPhoneNumbersByRestaurantId(int restaurantId) {
        List<String> phoneNumbers = new ArrayList<>();
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantException("Restaurant not found."));

        phoneNumbers.add(restaurant.getPhoneNumber1());
        phoneNumbers.add(restaurant.getPhoneNumber2());
        phoneNumbers.add(restaurant.getPhoneNumber3());

        return phoneNumbers;
    }

    @Override
    public Restaurant updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant) {
        Restaurant existingRestaurant = getRestaurantById(restaurantId);

        existingRestaurant.setName(updatedRestaurant.getName());
        //existingRestaurant.setTablesCount(updatedRestaurant.getTablesCount());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setPhoneNumber1(updatedRestaurant.getPhoneNumber1());
        existingRestaurant.setPhoneNumber2(updatedRestaurant.getPhoneNumber2());
        existingRestaurant.setPhoneNumber3(updatedRestaurant.getPhoneNumber3());

        return restaurantRepository.save(existingRestaurant);
    }
}
