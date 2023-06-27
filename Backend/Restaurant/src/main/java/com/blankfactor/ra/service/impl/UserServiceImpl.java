package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.AdminDto;
import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.WaiterDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.RestaurantException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.exceptions.custom.UserRoleException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public AppUser createWaiter(WaiterDto waiterDto) {
        return createUserWithEmailAndRoleType(waiterDto.getEmail(), RoleType.WAITER, waiterDto.getRestaurant());
    }

    @Override
    public AppUser createAdmin(AdminDto adminDto) {
        return createUserWithEmailAndRoleType(adminDto.getEmail(), RoleType.ADMIN, adminDto.getRestaurant());
    }

    @Override
    public AppUser addRoleToUser(UpdateUserDto updateUserDto) {
        AppUser appUser = userRepository.findAppUserByEmail(updateUserDto.getEmail())
                .orElseThrow(() -> new UserException("User with email " + updateUserDto.getEmail() + " not found"));

        return assignUserRole(updateUserDto, appUser);
    }

    @Override
    public UpdateUserDto getUserById(int userId, int restaurantId) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));

        UserRole userRole = userRoleRepository.findByAppUserAndRestaurant(appUser, restaurant)
                .orElseThrow(() -> new UserRoleException("No user/restaurant found"));


        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .email(appUser.getEmail())
                .name(appUser.getName())
                .surname(appUser.getSurname())
                .roleType(userRole.getRoleType())
                .build();

        return updateUserDto;
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UserException("User with email " + email + " not found"));
    }

    //TODO admins onboarding
    @Override
    public List<AppUser> getAllAdminsByRestaurantId(int restaurantId) {
        List<UserRole> userRoles = userRoleRepository.findAllByRestaurantIdAndRoleType(restaurantId, RoleType.ADMIN);
        List<AppUser> admins = new ArrayList<>();

        userRoles.forEach(userRole -> admins.add(userRole.getAppUser()));

        return admins;
    }

    @Transactional
    @Override
    public AppUser updateUserByEmail(UpdateUserDto updateUserDto) {
        AppUser appUserToUpdate = userRepository.findAppUserByEmail(updateUserDto.getEmail())
                .orElseThrow(() -> new UserException("User with email " + updateUserDto.getEmail() + " not found"));

        appUserToUpdate.setName(updateUserDto.getName());
        appUserToUpdate.setSurname(updateUserDto.getSurname());

        userRepository.save(appUserToUpdate);

        RoleType oldRoleType;
        if (updateUserDto.getRoleType() != null) {
            if (updateUserDto.getRoleType() == RoleType.ADMIN) {
                oldRoleType = RoleType.WAITER;
            } else {
                oldRoleType = RoleType.ADMIN;
            }
            UserRole userRoleToDelete = userRoleRepository
                    .findByAppUserAndRestaurantAndRoleType(appUserToUpdate, updateUserDto.getRestaurant(), oldRoleType)
                    .orElseThrow(() -> new UserException("No such record in UserRole table"));

            userRoleRepository.delete(userRoleToDelete);

            UserRole userRoleToUpdate = UserRole.builder()
                    .appUser(appUserToUpdate)
                    .restaurant(updateUserDto.getRestaurant())
                    .roleType(updateUserDto.getRoleType())
                    .build();

            userRoleRepository.save(userRoleToUpdate);
        }

        return appUserToUpdate;
    }

    @Override
    public void deleteUserById(int id) {
        List<UserRole> userRoles = userRoleRepository.findByAppUser_Id(id);

        if (userRoles.isEmpty()) {
            throw new UserException("UserRoles not found for User with id " + id);
        }

        userRoleRepository.deleteAll(userRoles);
        userRepository.deleteById(id);
    }

    private AppUser assignUserRole(UpdateUserDto updateUserDto, AppUser appUser) {
        UserRole userRole = UserRole.builder()
                .appUser(appUser)
                .roleType(updateUserDto.getRoleType())
                .restaurant(updateUserDto.getRestaurant())
                .build();

        userRoleRepository.save(userRole);

        return appUser;
    }

    private AppUser createUserWithEmailAndRoleType(String email, RoleType roleType, Restaurant restaurant) {
        AppUser user = AppUser.builder()
                .email(email)
                .build();

        Restaurant restaurantRetrieved = restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new RestaurantException("No restaurant with id " + restaurant.getId()));

        UserRole userRole = UserRole.builder()
                .appUser(user)
                .roleType(roleType)
                .restaurant(restaurantRetrieved)
                .build();

        AppUser savedAppUser = userRepository.save(user);
        userRoleRepository.save(userRole);

        return savedAppUser;
    }
}