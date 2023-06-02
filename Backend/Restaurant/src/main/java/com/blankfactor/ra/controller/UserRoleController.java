package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.UserRoleDto;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/user_role")
@RestController
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping("")
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRoleDto userRoleDto) {
        UserRole createdUserRole = userRoleService.saveUserRole(userRoleDto);

        return ResponseEntity.ok(createdUserRole);
    }
}
