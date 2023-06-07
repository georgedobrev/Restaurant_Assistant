package com.blankfactor.ra.config;

import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AppConfig {
    @Value("${base.url}")
    private String baseUrl;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
