package com.blankfactor.ra.config;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;
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
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(UserDto.class, AppUser.class)
                .addMappings(mapper -> mapper.skip(AppUser::setId));

        modelMapper.createTypeMap(UpdateUserDto.class, AppUser.class)
                .addMappings(mapper -> mapper.skip(AppUser::setId));

        return modelMapper;
    }
}
