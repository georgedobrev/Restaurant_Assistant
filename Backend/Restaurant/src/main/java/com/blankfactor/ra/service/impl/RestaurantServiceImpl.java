package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.CreateRestaurantDto;
import com.blankfactor.ra.dto.RestaurantDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.AppTableRepository;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.RestaurantService;
import jakarta.transaction.Transactional;
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
    private final AppTableRepository appTableRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantDto createRestaurantDto) {
        RestaurantDto restaurantDto = createRestaurantDto.getRestaurantDto();

        AppUser appUser = userRepository.findById(createRestaurantDto.getUserId())
                .orElseThrow(() -> new UserException("User with id " + createRestaurantDto.getUserId() + " not found"));

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantDto.getName())
                .tablesCount(0)
                .address(restaurantDto.getAddress())
                .phoneNumber1(restaurantDto.getPhoneNumber1())
                .phoneNumber2(restaurantDto.getPhoneNumber2())
                .phoneNumber3(restaurantDto.getPhoneNumber3())
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        UserRole userRole = UserRole.builder()
                .appUser(appUser)
                .restaurant(savedRestaurant)
                .roleType(RoleType.ADMIN)
                .build();
        userRoleRepository.save(userRole);

        return savedRestaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Restaurant getRestaurantById(Integer restaurantId) {
        return restaurantRepository.findByIdAndDeletedIsFalse(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));
    }

    @Override
    public List<Restaurant> getAllRestaurantsByAdmin(Integer userId) {
        List<UserRole> userRoles = userRoleRepository.findByAppUserIdAndRoleType(userId, RoleType.ADMIN);
        List<Restaurant> userRestaurant = new ArrayList<>();

        for (UserRole userRole : userRoles) {
            userRestaurant.add(userRole.getRestaurant());
        }

        return userRestaurant;
    }

    @Override
    public Restaurant updateRestaurantById(Integer restaurantId, RestaurantDto updatedRestaurant) {
        Restaurant existingRestaurant = getRestaurantById(restaurantId);

        existingRestaurant.setName(updatedRestaurant.getName());
        existingRestaurant.setAddress(updatedRestaurant.getAddress());
        existingRestaurant.setPhoneNumber1(updatedRestaurant.getPhoneNumber1());
        existingRestaurant.setPhoneNumber2(updatedRestaurant.getPhoneNumber2());
        existingRestaurant.setPhoneNumber3(updatedRestaurant.getPhoneNumber3());

        return restaurantRepository.save(existingRestaurant);
    }

    // TODO Implement soft delete for sections and shifts!
    @Transactional
    @Override
    public void deleteRestaurantById(Integer restaurantId) {
        Restaurant restaurant = getRestaurantById(restaurantId);
        restaurantRepository.softDeleteRestaurant(restaurantId);
        appTableRepository.softDeleteAppTablesByRestaurantId(restaurantId);
    }
}