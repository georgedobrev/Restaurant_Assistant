package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.config.AppProp;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.exceptions.custom.AuthenticationException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.service.GoogleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@AllArgsConstructor
@Service
public class GoogleServiceImpl implements GoogleService {
    private final AppProp appProp;

    @Override
    public UserDto buildUserDtoFromGoogleToken(String googleToken) throws GeneralSecurityException, IOException {
        GoogleIdToken idToken = verifyGoogleToken(googleToken);

        if (idToken != null) {
            Payload payload = idToken.getPayload();

            String nameAndSurname = (String) payload.get("name");
            String[] nameSplitted = nameAndSurname.split(" ");
            String name = nameSplitted[0];
            String surname = nameSplitted[1];

            return UserDto.builder()
                    .name(name)
                    .surname(surname)
                    .email(payload.getEmail())
                    .password(appProp.getGooglePassword())
                    .build();
        } else {
            throw new AuthenticationException("Invalid ID token");
        }
    }

    @Override
    public GoogleIdToken verifyGoogleToken(String token) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(appProp.getClientId()))
                .build();

        return verifier.verify(token);
    }
}
