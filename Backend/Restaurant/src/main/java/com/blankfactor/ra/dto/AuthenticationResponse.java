package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.LoginResponseRoleType;
import com.blankfactor.ra.model.AppUser;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String token;
    private String refreshToken;
    private AppUser appUser;
    private LoginResponseRoleType roleType;
}