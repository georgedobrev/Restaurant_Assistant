package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.EmployeeDto;
import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createEmployee(EmployeeDto employeeDto);

    AppUser addRoleToUser(UpdateUserDto updateUserDto);

    List<AppUser> getAllAdminsByRestaurantId(int restaurantId);

    UpdateUserDto getUserById(int id, int restaurantId);

    AppUser getUserByEmail(String email);

    AppUser updateUserByEmail(UpdateUserDto updateUserDto);

    void deleteUserById(int id);
}