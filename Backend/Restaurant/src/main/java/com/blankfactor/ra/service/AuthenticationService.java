package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.AuthenticationRequestDto;
import com.blankfactor.ra.dto.AuthenticationResponse;
import com.blankfactor.ra.dto.GoogleTokenDto;
import com.blankfactor.ra.dto.UserDto;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthenticationService {
    AuthenticationResponse login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException;

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequestDto request);
}
