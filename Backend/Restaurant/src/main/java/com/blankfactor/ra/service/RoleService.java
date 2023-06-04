package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.RoleDto;
import com.blankfactor.ra.model.Role;

public interface RoleService {
    Role save(RoleDto roleDto);

    void deleteRoleById(int id);
}
