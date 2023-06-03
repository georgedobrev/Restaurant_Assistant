package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.exceptions.RestaurantException;
import com.blankfactor.ra.exceptions.RoleException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Role;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.RoleRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public AppUser saveUser(UserDto userDto) {
        AppUser appUser = new AppUser();

        appUser.setEmail(userDto.getEmail());
        appUser.setName(userDto.getName());
        appUser.setSurname(userDto.getSurname());
        appUser.setBlacklisted(false);
        appUser.setActive(true);
        appUser.setCreatedAt(Instant.now());

        AppUser savedAppUser = userRepository.save(appUser);

        UserRole userRole = new UserRole();
        userRole.setAppUser(savedAppUser);
        Role role = roleRepository.findById(userDto.getRoleId()).orElse(null);

        if (role == null) {
            throw new RoleException("Role not found");
        } else {
            userRole.setRole(role);
        }

        Restaurant restaurant = restaurantRepository.findById(userDto.getRestaurantId()).orElse(null);

        if (restaurant == null) {
            throw new RestaurantException("Restaurant not found");
        } else {
            userRole.setRestaurant(restaurant);
        }

        userRoleRepository.save(userRole);

        return savedAppUser;
    }
}
