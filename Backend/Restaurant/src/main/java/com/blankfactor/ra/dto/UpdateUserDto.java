package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RoleType;
import lombok.Data;

@Data
public class UpdateUserDto extends UserDto {
    private RoleType roleAfter;
}
