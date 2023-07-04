package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createEmployee(EmployeeDto employeeDto);

    AppUser addRoleToUser(UpdateUserDto updateUserDto);

    List<AppUser> getAllAdminsByRestaurantId(int restaurantId);

    GetUserResponseDto getUserById(int id, int restaurantId);

    AppUser getUserByEmail(String email);

    AppUser updateUserDetails(UpdateUserDetailsDto updateUserDetailsDto);

    AppUser updateUserRole(UpdateUserRoleDto updateUserRoleDto);

    void deleteUserById(int id);
}