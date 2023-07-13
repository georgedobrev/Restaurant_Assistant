package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.EmployeeDto;
import com.blankfactor.ra.dto.EmployeeResponseDto;
import com.blankfactor.ra.dto.UpdateUserDetailsDto;
import com.blankfactor.ra.dto.UpdateUserRoleDto;
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
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public AppUser createEmployee(EmployeeDto employeeDto) {
        Optional<AppUser> appUser = userRepository.findAppUserByEmail(employeeDto.getEmail());

        Restaurant restaurantRetrieved = restaurantRepository.findById(employeeDto.getRestaurant().getId())
                .orElseThrow(() -> new RestaurantException("No restaurant with id " + employeeDto.getRestaurant().getId()));

        if (appUser.isPresent()) {
            Optional<UserRole> userRole = userRoleRepository
                    .findByAppUserAndRestaurantAndRoleType(appUser.get(), restaurantRetrieved, employeeDto.getRoleType());

            if (userRole.isEmpty()) {

                UserRole newUserRole = UserRole.builder()
                        .appUser(appUser.get())
                        .roleType(employeeDto.getRoleType())
                        .restaurant(restaurantRetrieved)
                        .build();

                userRoleRepository.save(newUserRole);
            }

            return appUser.get();
        } else {
            AppUser user = AppUser.builder()
                    .email(employeeDto.getEmail())
                    .build();

            UserRole userRole = UserRole.builder()
                    .appUser(user)
                    .roleType(employeeDto.getRoleType())
                    .restaurant(restaurantRetrieved)
                    .build();

            AppUser savedAppUser = userRepository.save(user);

            userRoleRepository.save(userRole);
            return savedAppUser;
        }
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Integer userId, Integer restaurantId) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException("Restaurant with id " + restaurantId + " not found"));

        UserRole userRole = userRoleRepository.findByAppUserAndRestaurant(appUser, restaurant)
                .orElseThrow(() -> new UserRoleException("No user/restaurant found"));


        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .email(appUser.getEmail())
                .name(appUser.getName())
                .surname(appUser.getSurname())
                .roleType(userRole.getRoleType())
                .build();

        return employeeResponseDto;
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UserException("User with email " + email + " not found"));
    }

    //TODO admins onboarding
    @Override
    public List<AppUser> getAllAdminsByRestaurantId(Integer restaurantId) {
        List<UserRole> userRoles = userRoleRepository.findAllByRestaurantIdAndRoleType(restaurantId, RoleType.ADMIN);
        List<AppUser> admins = new ArrayList<>();

        userRoles.forEach(userRole -> admins.add(userRole.getAppUser()));

        return admins;
    }

    @Transactional
    @Override
    public AppUser updateUserInfo(UpdateUserDetailsDto updateUserDetailsDto) {
        AppUser appUserToUpdate = userRepository.findAppUserByEmail(updateUserDetailsDto.getEmail())
                .orElseThrow(() -> new UserException("User with email " + updateUserDetailsDto.getEmail() + " not found"));

        appUserToUpdate.setName(updateUserDetailsDto.getName());
        appUserToUpdate.setSurname(updateUserDetailsDto.getSurname());

        return userRepository.save(appUserToUpdate);
    }

    @Transactional
    @Override
    public AppUser updateUserRole(UpdateUserRoleDto updateUserRoleDto) {
        AppUser appUserToUpdate = userRepository.findAppUserByEmail(updateUserRoleDto.getEmail())
                .orElseThrow(() -> new UserException("User with email " + updateUserRoleDto.getEmail() + " not found"));

        RoleType oldRoleType;
        if (updateUserRoleDto.getRoleType() != null) {
            if (updateUserRoleDto.getRoleType() == RoleType.ADMIN) {
                oldRoleType = RoleType.WAITER;
            } else {
                oldRoleType = RoleType.ADMIN;
            }
            UserRole userRoleToDelete = userRoleRepository
                    .findByAppUserAndRestaurantAndRoleType(appUserToUpdate, updateUserRoleDto.getRestaurant(), oldRoleType)
                    .orElseThrow(() -> new UserException("No such record in UserRole table"));

            userRoleRepository.delete(userRoleToDelete);

            UserRole userRoleToUpdate = UserRole.builder()
                    .appUser(appUserToUpdate)
                    .restaurant(updateUserRoleDto.getRestaurant())
                    .roleType(updateUserRoleDto.getRoleType())
                    .build();

            userRoleRepository.save(userRoleToUpdate);
        }

        return appUserToUpdate;
    }

    @Override
    public void deleteUserByEmail(String email) {
        AppUser appUser = userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UserException("User with email " + email + " does not exist"));

        List<UserRole> userRoles = userRoleRepository.findByAppUser_Id(appUser.getId());

        if (userRoles.isEmpty()) {
            throw new UserException("UserRoles not found for User with email " + email);
        }

        userRoleRepository.deleteAll(userRoles);
        userRepository.deleteById(appUser.getId());
    }
}