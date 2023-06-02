package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.User;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        User createdUser = userService.saveUser(userDto);

        return ResponseEntity.ok(createdUser);
    }
}
