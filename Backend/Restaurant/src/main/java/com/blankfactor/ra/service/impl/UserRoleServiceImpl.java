package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UserRoleDto;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public UserRole saveUserRole(UserRoleDto userRoleDto) {
        UserRole userRole = new UserRole();

        userRole.setUser(userRoleDto.getUser());
        userRole.setRole(userRoleDto.getRole());
        userRole.setRestaurant(userRoleDto.getRestaurant());

        return userRoleRepository.save(userRole);
    }
}
