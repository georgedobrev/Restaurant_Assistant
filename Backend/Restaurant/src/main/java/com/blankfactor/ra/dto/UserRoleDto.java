package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.Role;
import com.blankfactor.ra.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {

    private User user;
    private Role role;
    private Restaurant restaurant;
}
