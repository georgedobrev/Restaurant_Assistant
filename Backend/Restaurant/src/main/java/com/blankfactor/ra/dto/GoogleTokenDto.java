package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.LoginRequestRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleTokenDto {
    private String googleJwt;
    private LoginRequestRoleType loginRequestRoleType;
}