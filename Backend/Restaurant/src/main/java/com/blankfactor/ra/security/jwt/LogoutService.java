package com.blankfactor.ra.security.jwt;

import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.repository.RefreshTokenRepository;
import com.blankfactor.ra.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            return;
        }

        jwt = authHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        deleteUserToken(username);
        SecurityContextHolder.clearContext();
    }

    @Transactional
    private void deleteUserToken(String username) {
        AppUser appUser = userRepository.findAppUserByEmail(username).orElseThrow(() -> new UserException("User not found"));
        refreshTokenRepository.deleteByAppUser(appUser);
    }
}
