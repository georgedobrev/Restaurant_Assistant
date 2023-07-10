package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.LoginResponseRoleType;
import com.blankfactor.ra.model.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {
    private String token;
    private String refreshToken;
    private AppUser appUser;
    private LoginResponseRoleType roleType;
}