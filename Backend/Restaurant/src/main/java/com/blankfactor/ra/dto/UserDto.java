package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String name;
    private String surname;
    private RoleType roleType;
    private Restaurant restaurant;
}
