package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.enums.LoginRequestRoleType;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthenticationService {
    AuthenticationResponseDto login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException;

    AuthenticationResponseDto register(UserDto userDto, LoginRequestRoleType loginRequestRoleType);

    AuthenticationResponseDto authenticate(UserDto userDto, LoginRequestRoleType loginRequestRoleType);

    JwtTokenDto jwtFromRefreshToken(RefreshTokenDto refreshTokenDto);
}