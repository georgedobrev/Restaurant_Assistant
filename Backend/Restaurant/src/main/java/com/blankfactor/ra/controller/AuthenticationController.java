package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.enums.LoginRequestRoleType;
import com.blankfactor.ra.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    // Todo make sure the second time we call the /login we receive the same refresh from db
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody GoogleTokenDto googleJwt) throws GeneralSecurityException, IOException {
        return ResponseEntity.ok(authenticationService.login(googleJwt));
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto, LoginRequestRoleType loginRequestRoleType) {
        return ResponseEntity.ok(authenticationService.register(userDto, loginRequestRoleType));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.authenticate(userDto));
    }

    @GetMapping("/jwt")
    public ResponseEntity<JwtTokenDto> jwtFromRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok(authenticationService.jwtFromRefreshToken(refreshTokenDto));
    }
}