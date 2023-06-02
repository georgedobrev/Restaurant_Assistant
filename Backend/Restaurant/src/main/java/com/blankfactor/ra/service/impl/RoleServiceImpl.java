package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.RoleDto;
import com.blankfactor.ra.model.Role;
import com.blankfactor.ra.repository.RoleRepository;
import com.blankfactor.ra.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(RoleDto roleDto) {
        Role role = new Role();

        role.setType(roleDto.getType());

        return roleRepository.save(role);
    }
}
