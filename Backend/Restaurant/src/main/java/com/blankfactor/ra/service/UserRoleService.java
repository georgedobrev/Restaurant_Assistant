package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UserRoleDto;
import com.blankfactor.ra.model.UserRole;

public interface UserRoleService {
    UserRole saveUserRole(UserRoleDto userRoleDto);
}
