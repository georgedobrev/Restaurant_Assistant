package com.blankfactor.ra.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppProp {
    @Value("${application.security.jwtSecretKey}")
    private String secretKey;

    @Value("${application.security.jwtExpirationMs}")
    private long jwtExpiration;

    @Value("${application.security.jwtRefreshExpiration}")
    private long jwtRefreshExpiration;

    @Value("${base.url}")
    private String baseUrl;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
