package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto extends UserDto {
    private RoleType roleAfter;
}
