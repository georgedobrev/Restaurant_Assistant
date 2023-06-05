package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.exceptions.RestaurantException;
import com.blankfactor.ra.exceptions.RoleException;
import com.blankfactor.ra.exceptions.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
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

        return assignUserRole(userDto, savedAppUser);
    }

    @Override
    public AppUser addRoleToUser(UserDto userDto) {
        AppUser appUser = userRepository.findAppUserByEmail(userDto.getEmail());

        if (appUser ==  null) {
            throw new UserException("User with email " + userDto.getEmail() + " not found");
        }

        return assignUserRole(userDto, appUser);
    }

    @Override
    public AppUser getUserById(int id) {
        AppUser appUser = userRepository.findById(id).orElse(null);

        if (appUser ==  null) {
            throw new UserException("User with id " + id + " not found");
        }

        return appUser;
    }

    @Override
    public void deleteUserById(int id) {
        AppUser appUser = userRepository.findById(id).orElse(null);

        List<UserRole> userRoles = userRoleRepository.findByAppUser(appUser);

        if (userRoles.isEmpty()) {
            throw new UserException("UserRoles not found for User with id " + id);
        }

        userRoleRepository.deleteAll(userRoles);

        if (appUser == null) {
            throw new UserException("User with id " + id + " not found");
        }

        userRepository.delete(appUser);
    }

    private AppUser assignUserRole(UserDto userDto, AppUser appUser) {
        UserRole userRole = new UserRole();

        userRole.setAppUser(appUser);

        userRole.setRole(userDto.getRoleType());

        Restaurant restaurant = restaurantRepository.findById(userDto.getRestaurantId()).orElse(null);

        if (restaurant == null) {
            throw new RestaurantException("Restaurant with id " + userDto.getRestaurantId() + " not found");
        } else {
            userRole.setRestaurant(restaurant);
        }

        userRoleRepository.save(userRole);

        return appUser;
    }
}
