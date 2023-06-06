package com.blankfactor.ra.model;

import com.blankfactor.ra.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolePK implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Integer appUser;
    private Integer restaurant;
    private RoleType roleType;
}