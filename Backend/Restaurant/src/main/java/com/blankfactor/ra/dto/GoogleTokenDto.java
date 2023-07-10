package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.LoginRequestRoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleTokenDto {
    private String googleJwt;
    private LoginRequestRoleType loginRequestRoleType;
}