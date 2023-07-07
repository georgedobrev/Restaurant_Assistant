package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.*;
import com.blankfactor.ra.enums.LoginRequestRoleType;
import com.blankfactor.ra.enums.LoginResponseRoleType;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.TenantException;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.model.Tenant;
import com.blankfactor.ra.model.UserRole;
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

import static com.blankfactor.ra.enums.LoginRequestRoleType.EMPLOYEE;
import static com.blankfactor.ra.enums.LoginRequestRoleType.USER;

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
    public AuthenticationResponse login(GoogleTokenDto googleToken) throws GeneralSecurityException, IOException {
        UserDto userDto = googleService.buildUserDtoFromGoogleToken(googleToken.getGoogleJwt());
        AuthenticationResponse authenticationResponse;

        switch (googleToken.getLoginRequestRoleType()) {
            case EMPLOYEE:

                try {
                    authenticationResponse = authenticate(userDto);
                    authenticationResponse.setRoleType(LoginResponseRoleType.USER);

                    return authenticationResponse;
                } catch (Exception exception) {
                    authenticationResponse = register(userDto, EMPLOYEE);
                    authenticationResponse.setRoleType(LoginResponseRoleType.USER);

                    return authenticationResponse;
                }
//            case EMPLOYEE:
//                try {
//
//                } catch (Exception exception) {
//
//                }
//
//                break;
//            default: throw new UserRoleException("No such role");
        }


        //UserDto userDto = googleService.buildUserDtoFromGoogleToken(googleToken.getGoogleJwt());

        System.out.println(googleToken);

//        AuthenticationRequestDto authenticationRequestDto = AuthenticationRequestDto.builder()
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .build();

//        //TODO Reconsider
//        try {
//            return authenticate(authenticationRequestDto);
//        } catch (Exception exception) {
//            return register(userDto);
//        }
        return null;
    }


    @Override
    public AuthenticationResponse register(UserDto userDto, LoginRequestRoleType loginRequestRoleType) {
        AppUser appUser = new AppUser();
        LoginResponseRoleType loginResponseRoleType = null;
        Map<String, Object> extraClaims = new HashMap<>();
        List<GrantedAuthority> authorities = new ArrayList<>();

        switch (loginRequestRoleType) {
            case EMPLOYEE -> {
                Optional<AppUser> appUserOptional = userRepository.findAppUserByEmail(userDto.getEmail());

                if (appUserOptional.isPresent()) {
                    Optional<Tenant> tenant = tenantRepository.findTenantByEmail(userDto.getEmail());
                    Optional<Sysadmin> sysadmin = sysadminRepository.findSysadminByEmail(userDto.getEmail());
                    UserRole userRoles = userRoleRepository.findUserRoleByAppUser(appUserOptional.get());

                    if (tenant.isPresent() || sysadmin.isPresent() || userRoles.getRoleType() == RoleType.WAITER || userRoles.getRoleType() == RoleType.ADMIN) {
                        appUser.setEmail(userDto.getEmail());
                        appUser.setName(userDto.getName());
                        appUser.setSurname(userDto.getSurname());
                        appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

                        if (tenant.isPresent()) {
                            loginResponseRoleType = LoginResponseRoleType.TENANT;
                            authorities.add(new SimpleGrantedAuthority("ROLE_TENANT"));
                            extraClaims.put("authorities", authorities);
                        } else if (sysadmin.isPresent()) {
                            loginResponseRoleType = LoginResponseRoleType.SYSADMIN;
                            authorities.add(new SimpleGrantedAuthority("ROLE_SYSADMIN"));
                            extraClaims.put("authorities", authorities);
                        } else if (userRoles.getRoleType() == RoleType.WAITER) {
                            loginResponseRoleType = LoginResponseRoleType.WAITER;
                            authorities.add(new SimpleGrantedAuthority("ROLE_WAITER"));
                            extraClaims.put("authorities", authorities);
                        } else if (userRoles.getRoleType() == RoleType.ADMIN) {
                            loginResponseRoleType = LoginResponseRoleType.ADMIN;
                            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                            extraClaims.put("authorities", authorities);
                        }
                    }
                }
            }
            case USER -> {
                loginResponseRoleType = LoginResponseRoleType.USER;
                appUser.setName(userDto.getName());
                appUser.setSurname(userDto.getSurname());
                appUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                extraClaims.put("authorities", authorities);
            }
        }

        String jwtToken = jwtService.generateJwtToken(extraClaims, appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);
        userRepository.save(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .appUser(appUser)
                .roleType(loginResponseRoleType)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(UserDto userDto) {
        //TODO: test if authenticate checks the data from the db
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        AppUser appUser = userRepository.findAppUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));

        String jwtToken = jwtService.generateJwtToken(appUser);
        String refreshToken = jwtService.generateRefreshToken(appUser);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .appUser(appUser)
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