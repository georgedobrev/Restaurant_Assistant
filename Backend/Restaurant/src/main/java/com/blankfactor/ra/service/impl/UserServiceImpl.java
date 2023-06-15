package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public AppUser createUser(UpdateUserDto userDto) {
        AppUser appUser = new AppUser();

        appUser.setEmail(userDto.getEmail());
        appUser.setName(userDto.getName());
        appUser.setSurname(userDto.getSurname());

        AppUser savedAppUser = userRepository.save(appUser);

        if (userDto.getRoleType() == RoleType.ADMIN || userDto.getRoleType() == RoleType.WAITER) {
            assignUserRole(userDto, savedAppUser);
        }

        return savedAppUser;
    }

    @Override
    public AppUser addRoleToUser(UpdateUserDto updateUserDto) {
        AppUser appUser = userRepository.findAppUserByEmail(updateUserDto.getEmail())
                .orElseThrow(() -> new UserException("User with email " + updateUserDto.getEmail() + " not found"));

        return assignUserRole(updateUserDto, appUser);
    }

    @Override
    public AppUser getUserById(int userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));
    }

    @Override
    public AppUser getUserByEmail(String email) {
        return userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UserException("User with email " + email + " not found"));
    }

    @Transactional
    @Override
    public AppUser updateUserById(int userId, UpdateUserDto updateUserDto) {
        AppUser appUserToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        UserRole userRoleToDelete = userRoleRepository
                .findByAppUserAndRestaurantAndRoleType(appUserToUpdate, updateUserDto.getRestaurant(), updateUserDto.getRoleType())
                .orElseThrow(() -> new UserException("No such record in UserRole table"));

        userRoleRepository.delete(userRoleToDelete);

        appUserToUpdate.setEmail(updateUserDto.getEmail());
        appUserToUpdate.setName(updateUserDto.getName());
        appUserToUpdate.setSurname(updateUserDto.getSurname());

        userRepository.save(appUserToUpdate);

        UserRole userRoleToUpdate = UserRole.builder()
                .appUser(appUserToUpdate)
                .restaurant(updateUserDto.getRestaurant())
                .roleType(updateUserDto.getRoleType())
                .build();

        userRoleRepository.save(userRoleToUpdate);

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

    public AppUser assignUserRole(UpdateUserDto updateUserDto, AppUser appUser) {
        UserRole userRole = UserRole.builder()
                .appUser(appUser)
                .roleType(updateUserDto.getRoleType())
                .restaurant(updateUserDto.getRestaurant())
                .build();

        userRoleRepository.save(userRole);

        return appUser;
    }
}