package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthenticationService {
    AuthenticationResponse login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException;

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequestDto request);

    JwtTokenDto jwtFromRefreshToken(RefreshTokenDto refreshTokenDto);
}