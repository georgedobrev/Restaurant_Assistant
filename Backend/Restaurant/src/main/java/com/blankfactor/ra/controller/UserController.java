package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<AppUser> createUser(@RequestBody UserDto userDto) {
        AppUser createdAppUser = userService.saveUser(userDto);

        return new ResponseEntity<>(createdAppUser, HttpStatus.CREATED);
    }

    @PostMapping("/addRole")
    public ResponseEntity<AppUser> addRoleToUser(@RequestBody UserDto userDto) {
        AppUser createdAppUser = userService.addRoleToUser(userDto);

        return ResponseEntity.ok(createdAppUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable int id) {
        AppUser appUser = userService.getUserById(id);

        return ResponseEntity.ok(appUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable int id) {
        userService.deleteUserById(id);

        return ResponseEntity.ok().build();
    }
}
