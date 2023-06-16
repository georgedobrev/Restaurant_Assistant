package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserEmailDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<AppUser> createUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        AppUser createdAppUser = userService.createUser(updateUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppUser);
    }

    @PostMapping("/addRole")
    public ResponseEntity<AppUser> addRoleToUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        AppUser createdAppUser = userService.addRoleToUser(updateUserDto);

        return ResponseEntity.ok(createdAppUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<AppUser> getUserById(@PathVariable int userId) {
        AppUser appUser = userService.getUserById(userId);

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("")
    public ResponseEntity<AppUser> getUserByEmail(@Valid @RequestBody UserEmailDto userEmailDto) {
        AppUser appUser = userService.getUserByEmail(userEmailDto.getEmail());

        return ResponseEntity.ok(appUser);
    }

    @GetMapping("/all-admins/{restaurantId}")
    public ResponseEntity<List<AppUser>> getAllAdminsForRestaurant(@PathVariable("restaurantId") int restaurantId) {
        List<AppUser> admins = userService.getAllAdminsByRestaurantId(restaurantId);

        return ResponseEntity.ok(admins);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<AppUser> updateUserById(@PathVariable int userId,
                                                  @RequestBody UpdateUserDto updateUserDto) {
        AppUser updatedAppUser = userService.updateUserById(userId, updateUserDto);

        return ResponseEntity.ok(updatedAppUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);

        return ResponseEntity.ok().build();
    }
}
