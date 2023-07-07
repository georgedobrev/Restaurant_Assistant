package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.enums.LoginRequestRoleType;
import com.blankfactor.ra.enums.LoginResponseRoleType;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.AuthenticationException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.repository.SysadminRepository;
import com.blankfactor.ra.repository.TenantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.security.jwt.JwtService;
import com.blankfactor.ra.service.AuthenticationService;
import com.blankfactor.ra.service.GoogleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;

import static com.blankfactor.ra.enums.LoginRequestRoleType.*;
import static com.blankfactor.ra.enums.LoginResponseRoleType.*;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final GoogleService googleService;
    private final TenantRepository tenantRepository;
    private final SysadminRepository sysadminRepository;
    private final UserRoleRepository userRoleRepository;

    @Override
    public AuthenticationResponseDto login(GoogleTokenDto googleTokenDto) throws GeneralSecurityException, IOException {
        UserDto userDto = googleService.buildUserDtoFromGoogleToken(googleTokenDto.getGoogleJwt());
        AuthenticationResponseDto authenticationResponse;

        try {
            authenticationResponse = authenticate(userDto, googleTokenDto.getLoginRequestRoleType());

            return authenticationResponse;
        } catch (Exception exception) {
            authenticationResponse = register(userDto, googleTokenDto.getLoginRequestRoleType());

            return authenticationResponse;
        }

    }


    @Override
    public AuthenticationResponseDto register(UserDto userDto, LoginRequestRoleType loginRequestRoleType) {
        AppUser appUser = new AppUser();
        LoginResponseRoleType loginResponseRoleType = null;
        Map<String, Object> extraClaims = new HashMap<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (loginRequestRoleType == APPEXECUTIVE || loginRequestRoleType == APPWAITER) {
            appUser = userRepository.findAppUserByEmail(userDto.getEmail())
                    .orElseThrow(() -> new UserException("User not found"));

            appUser.setName(userDto.getName());
            appUser.setSurname(userDto.getSurname());
            appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        switch (loginRequestRoleType) {
            case APPEXECUTIVE -> {
                boolean isAppUserAdmin = userRoleRepository.findUserRoleByAppUser(appUser)
                        .stream()
                        .findAny()
                        .map(role -> role.getRoleType().equals(RoleType.ADMIN))
                        .orElse(false);

                Optional<Tenant> tenant = tenantRepository.findTenantByEmail(userDto.getEmail());
                Optional<Sysadmin> sysadmin = sysadminRepository.findSysadminByEmail(userDto.getEmail());

                if (sysadmin.isPresent()) {
                    loginResponseRoleType = SYSADMIN;
                    authorities.add(new SimpleGrantedAuthority(SYSADMIN.name()));
                } else if (tenant.isPresent()) {
                    loginResponseRoleType = TENANT;
                    authorities.add(new SimpleGrantedAuthority(TENANT.name()));
                } else if (isAppUserAdmin) {
                    loginResponseRoleType = ADMIN;
                    authorities.add(new SimpleGrantedAuthority(ADMIN.name()));
                }
            }
            case APPWAITER -> {
                loginResponseRoleType = WAITER;
                authorities.add(new SimpleGrantedAuthority(WAITER.name()));
            }
            case APPUSER -> {
                appUser.setEmail(userDto.getEmail());
                appUser.setName(userDto.getName());
                appUser.setSurname(userDto.getSurname());
                appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
                loginResponseRoleType = USER;
                authorities.add(new SimpleGrantedAuthority(USER.name()));
            }
            default -> throw new AuthenticationException("Wrong role request type");
        }

        extraClaims.put("authorities", authorities);
        String jwtToken = jwtService.generateJwtToken(extraClaims, appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        userRepository.save(appUser);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .appUser(appUser)
                .roleType(loginResponseRoleType)
                .build();
    }

    @Override
    public AuthenticationResponseDto authenticate(UserDto userDto, LoginRequestRoleType loginRequestRoleType) {
        //TODO: test if authenticate checks the data from the db
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        AppUser appUser = userRepository.findAppUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        LoginResponseRoleType loginResponseRoleType = null;
        Map<String, Object> extraClaims = new HashMap<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (loginRequestRoleType) {
            case APPEXECUTIVE -> {
                boolean isAppUserAdmin = userRoleRepository.findUserRoleByAppUser(appUser)
                        .stream()
                        .findAny()
                        .map(role -> role.getRoleType().equals(RoleType.ADMIN))
                        .orElse(false);

                Optional<Tenant> tenant = tenantRepository.findTenantByEmail(userDto.getEmail());
                Optional<Sysadmin> sysadmin = sysadminRepository.findSysadminByEmail(userDto.getEmail());

                //TODO: If sysadmin can never log in as tenant/admin
                if (sysadmin.isPresent()) {
                    loginResponseRoleType = SYSADMIN;
                    authorities.add(new SimpleGrantedAuthority(SYSADMIN.name()));
                } else if (tenant.isPresent()) {
                    loginResponseRoleType = TENANT;
                    authorities.add(new SimpleGrantedAuthority(TENANT.name()));
                } else if (isAppUserAdmin) {
                    loginResponseRoleType = ADMIN;
                    authorities.add(new SimpleGrantedAuthority(ADMIN.name()));
                }
            }
            case APPWAITER -> {
                loginResponseRoleType = WAITER;
                authorities.add(new SimpleGrantedAuthority(WAITER.name()));
            }
            case APPUSER -> {
                loginResponseRoleType = USER;
                authorities.add(new SimpleGrantedAuthority(USER.name()));
            }
            default -> throw new AuthenticationException("Wrong role request type");
        }

        extraClaims.put("authorities", authorities);
        String jwtToken = jwtService.generateJwtToken(extraClaims, appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .appUser(appUser)
                .roleType(loginResponseRoleType)
                .build();
    }

    @Override
    public JwtTokenDto jwtFromRefreshToken(RefreshTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefreshToken();

        String email = jwtService.extractUsername(refreshToken);

        AppUser appUserFromClaims = userRepository.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        return JwtTokenDto.builder()
                .jwtToken(jwtService.generateJwtToken(appUserFromClaims))
                .build();
    }
}