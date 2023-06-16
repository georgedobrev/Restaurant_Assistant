package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.AuthenticationRequestDto;
import com.blankfactor.ra.dto.AuthenticationResponse;
import com.blankfactor.ra.dto.GoogleTokenDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.security.jwt.JwtService;
import com.blankfactor.ra.service.AuthenticationService;
import com.blankfactor.ra.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final GoogleService googleService;

    @Override
    public AuthenticationResponse login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException {
        UserDto userDto = googleService.buildUserDtoFromGoogleToken(googleToken.getGoogleJwt());

        System.out.println(googleToken);

        AuthenticationRequestDto authenticationRequestDto = AuthenticationRequestDto.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        //TODO Reconsider
        try {
            return authenticate(authenticationRequestDto);
        } catch (Exception exception) {
            return register(userDto);
        }
    }

    @Override
    public AuthenticationResponse register(UserDto userDto) {
        AppUser appUser = AppUser.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();

        String jwtToken = jwtService.generateJwtToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        userRepository.save(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        AppUser appUser = userRepository.findAppUserByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        String jwtToken = jwtService.generateJwtToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }
}
