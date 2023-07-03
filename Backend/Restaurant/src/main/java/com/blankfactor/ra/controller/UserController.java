package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.AdminDto;
import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserEmailDto;
import com.blankfactor.ra.dto.WaiterDto;
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

    @PostMapping("/waiter")
    @Operation(summary = "Create waiter")
    public ResponseEntity<AppUser> createWaiter(@Valid @RequestBody WaiterDto waiterDto) {
        var createdWaiter = userService.createWaiter(waiterDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdWaiter);
    }

    @PostMapping("/admin")
    @Operation(summary = "Create admin")
    public ResponseEntity<AppUser> createAdmin(@Valid @RequestBody AdminDto adminDto) {
        var createdAdmin = userService.createAdmin(adminDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
    }

    @PostMapping("/role")
    @Operation(summary = "Add role to existing user")
    public ResponseEntity<AppUser> addRoleToUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        var createdAppUser = userService.addRoleToUser(updateUserDto);

        return ResponseEntity.ok(createdAppUser);
    }

    @GetMapping("/{userId}/{restaurantId}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UpdateUserDto> getUserById(@PathVariable int userId,
                                                     @PathVariable int restaurantId) {
        var appUser = userService.getUserById(userId, restaurantId);

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("")
    @Operation(summary = "Get user by email")
    public ResponseEntity<AppUser> getUserByEmail(@Valid @RequestBody UserEmailDto userEmailDto) {
        var appUser = userService.getUserByEmail(userEmailDto.getEmail());

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/admins/{restaurantId}")
    @Operation(summary = "Get all admins for a specific restaurant")
    public ResponseEntity<List<AppUser>> getAllAdminsByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        var admins = userService.getAllAdminsByRestaurantId(restaurantId);

        return ResponseEntity.ok(admins);
    }

    @PutMapping("")
    @Operation(summary = "Update user by email")
    public ResponseEntity<AppUser> updateUserByEmail(@RequestBody UpdateUserDto updateUserDto) {
        var updatedAppUser = userService.updateUserByEmail(updateUserDto);

        return ResponseEntity.ok(updatedAppUser);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user by id")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);

        return ResponseEntity.ok().build();
    }
}