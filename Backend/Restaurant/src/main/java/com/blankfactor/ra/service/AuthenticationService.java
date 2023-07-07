package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.enums.LoginRequestRoleType;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthenticationService {
    AuthenticationResponse login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException;

    AuthenticationResponse register(UserDto userDto, LoginRequestRoleType loginRequestRoleType);

    AuthenticationResponse authenticate(UserDto userDto);

    JwtTokenDto jwtFromRefreshToken(RefreshTokenDto refreshTokenDto);
}