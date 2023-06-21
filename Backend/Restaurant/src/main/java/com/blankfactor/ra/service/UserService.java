package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createUser(UpdateUserDto updateUserDto);

    AppUser addRoleToUser(UpdateUserDto updateUserDto);

    AppUser getUserById(int id);

    AppUser getUserByEmail(String email);

    void deleteUserById(int id);

    AppUser updateUserById(int id, UpdateUserDto updateUserDto);

    List<AppUser> getAllAdminsByRestaurantId(int restaurantId);

    AppUser assignUserRole(UpdateUserDto updateUserDto, AppUser appUser);
}
