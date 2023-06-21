package com.blankfactor.ra.controller;

import com.blankfactor.ra.dto.*;
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

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody GoogleTokenDto googleJwt) throws GeneralSecurityException, IOException {
        return ResponseEntity.ok(authenticationService.login(googleJwt));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequestDto request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/jwt")
    public ResponseEntity<JwtTokenDto> jwtFromRefreshToken(@RequestBody RefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok(authenticationService.jwtFromRefreshToken(refreshTokenDto));
    }
}
