package com.blankfactor.ra.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${base.url}")
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
