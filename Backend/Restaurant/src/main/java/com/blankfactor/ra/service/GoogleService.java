package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UserDto;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleService {

    GoogleIdToken verifyGoogleToken(String token) throws GeneralSecurityException, IOException;

    UserDto buildUserDtoFromGoogleToken(String token) throws GeneralSecurityException, IOException;
}
