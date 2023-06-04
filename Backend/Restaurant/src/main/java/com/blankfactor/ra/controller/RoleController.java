package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.RoleDto;
import com.blankfactor.ra.model.Role;
import com.blankfactor.ra.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/role")
@RestController
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role createdRole = roleService.save(roleDto);

        return ResponseEntity.ok(createdRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable int id) {
        roleService.deleteRoleById(id);

        return ResponseEntity.ok().build();
    }
}
