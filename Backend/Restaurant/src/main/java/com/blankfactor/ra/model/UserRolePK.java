package com.blankfactor.ra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRolePK implements Serializable {

    private static final Long serialVersionUID = 1L;
    private Integer appUser;
    private Integer restaurant;
    private Integer role;
}