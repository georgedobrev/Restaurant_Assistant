package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.EmployeeDto;
import com.blankfactor.ra.dto.EmployeeResponseDto;
import com.blankfactor.ra.dto.UpdateUserDetailsDto;
import com.blankfactor.ra.dto.UpdateUserRoleDto;
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

    @GetMapping("/{email}")
    @Operation(summary = "Get user by email")
    public ResponseEntity<AppUser> getUserByEmail(@PathVariable String email) {
        var appUser = userService.getUserByEmail(email);

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

    @DeleteMapping("/{email}")
    @Operation(summary = "Delete user by email")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);

        return ResponseEntity.ok().build();
    }
}