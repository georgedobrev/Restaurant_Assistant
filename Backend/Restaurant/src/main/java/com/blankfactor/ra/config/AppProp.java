package com.blankfactor.ra.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:application.properties")
public class AppProp {
    private String jwtSecret;

    private long jwtExpiration;

    private long jwtRefreshExpiration;

    private String baseUrl;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
