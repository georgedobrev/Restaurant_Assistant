package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.EmployeeDto;
import com.blankfactor.ra.dto.EmployeeResponseDto;
import com.blankfactor.ra.dto.UpdateUserDetailsDto;
import com.blankfactor.ra.dto.UpdateUserRoleDto;
import com.blankfactor.ra.model.AppUser;

import java.util.List;

public interface UserService {
    AppUser createEmployee(EmployeeDto employeeDto);

    List<AppUser> getAllAdminsByRestaurantId(Integer restaurantId);

    EmployeeResponseDto getEmployeeById(Integer id, Integer restaurantId);

    AppUser getUserByEmail(String email);

    AppUser updateUserInfo(UpdateUserDetailsDto updateUserDetailsDto);

    AppUser updateUserRole(UpdateUserRoleDto updateUserRoleDto);

    void deleteUserById(Integer id);
}