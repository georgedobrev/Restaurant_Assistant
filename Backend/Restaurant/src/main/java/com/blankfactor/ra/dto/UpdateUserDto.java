package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.Restaurant;
import lombok.Data;

@Data
public class UpdateUserDto extends UserDto {
    private RoleType roleType;
    private Restaurant restaurant;
}
