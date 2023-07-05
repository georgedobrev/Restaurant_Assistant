package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("/employee")
    @Operation(summary = "Create employee")
    public ResponseEntity<AppUser> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        var createdEmployee = userService.createEmployee(employeeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping("/{userId}/{restaurantId}")
    @Operation(summary = "Get employee details")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Integer userId,
                                                               @PathVariable Integer restaurantId) {
        var employee = userService.getEmployeeById(userId, restaurantId);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("")
    @Operation(summary = "Get user by email")
    public ResponseEntity<AppUser> getUserByEmail(@Valid @RequestBody UserEmailDto userEmailDto) {
        var appUser = userService.getUserByEmail(userEmailDto.getEmail());

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/admins/{restaurantId}")
    @Operation(summary = "Get all admins for a specific restaurant")
    public ResponseEntity<List<AppUser>> getAllAdminsByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
        var admins = userService.getAllAdminsByRestaurantId(restaurantId);

        return ResponseEntity.ok(admins);
    }

    @PutMapping("/details")
    @Operation(summary = "Update user details")
    public ResponseEntity<AppUser> updateUserInfo(@RequestBody UpdateUserDetailsDto updateUserDetailsDto) {
        var updatedAppUser = userService.updateUserInfo(updateUserDetailsDto);

        return ResponseEntity.ok(updatedAppUser);
    }

    @PutMapping("/role")
    @Operation(summary = "Update user role")
    public ResponseEntity<AppUser> updateUserRole(@RequestBody UpdateUserRoleDto updateUserRoleDto) {
        var updatedAppUser = userService.updateUserRole(updateUserRoleDto);

        return ResponseEntity.ok(updatedAppUser);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by id")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);

        return ResponseEntity.ok().build();
    }
}