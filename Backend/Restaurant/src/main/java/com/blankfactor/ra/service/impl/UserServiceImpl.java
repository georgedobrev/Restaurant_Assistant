package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.RestaurantException;
import com.blankfactor.ra.exceptions.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    @Override
    public AppUser createUser(UserDto userDto) {
        AppUser appUser = new AppUser();

        modelMapper.map(userDto, appUser);

        AppUser savedAppUser = userRepository.save(appUser);

        if (userDto.getRoleType() == RoleType.ADMIN || userDto.getRoleType() == RoleType.WAITER) {
            assignUserRole(userDto, savedAppUser);
        }

        return savedAppUser;
    }

    @Override
    public AppUser addRoleToUser(UserDto userDto) {
        AppUser appUser = userRepository.findAppUserByEmail(userDto.getEmail()).orElseThrow(() -> new UserException("User with email " + userDto.getEmail() + " not found"));

        return assignUserRole(userDto, appUser);
    }

    @Override
    public AppUser getUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id " + id + " not found"));
    }

    @Transactional
    @Override
    public AppUser updateUserById(int id, UpdateUserDto updateUserDto) {
        AppUser appUserToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id " + id + " not found"));

        Restaurant restaurant = restaurantRepository.findById(updateUserDto.getRestaurantId())
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + updateUserDto.getRestaurantId() + " not found"));

        UserRole userRoleToDelete = userRoleRepository
                .findByAppUserAndRestaurantAndRoleType(appUserToUpdate, restaurant, updateUserDto.getRoleType())
                .orElseThrow(() -> new UserException("No such record in UserRole table"));

        userRoleRepository.delete(userRoleToDelete);

        modelMapper.map(updateUserDto, appUserToUpdate);

        userRepository.save(appUserToUpdate);

        UserRole userRoleToUpdate = UserRole.builder()
                .appUser(appUserToUpdate)
                .restaurant(restaurant)
                .roleType(updateUserDto.getRoleAfter())
                .build();

        userRoleRepository.save(userRoleToUpdate);

        return appUserToUpdate;
    }

    @Override
    public void deleteUserById(int id) {
        AppUser appUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User with id " + id + " not found"));

        List<UserRole> userRoles = userRoleRepository.findByAppUser(appUser);

        if (userRoles.isEmpty()) {
            throw new UserException("UserRoles not found for User with id " + id);
        }

        userRoleRepository.deleteAll(userRoles);
        userRepository.delete(appUser);
    }

    private AppUser assignUserRole(UserDto userDto, AppUser appUser) {
        Restaurant restaurant = restaurantRepository.findById(userDto.getRestaurantId())
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + userDto.getRestaurantId() + " not found"));

        UserRole userRole = UserRole.builder()
                .appUser(appUser)
                .roleType(userDto.getRoleType())
                .restaurant(restaurant)
                .build();

        userRoleRepository.save(userRole);

        return appUser;
    }
}
