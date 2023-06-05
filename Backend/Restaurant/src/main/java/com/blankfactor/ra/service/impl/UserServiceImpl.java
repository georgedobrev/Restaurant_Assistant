package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public AppUser saveUser(UserDto userDto) {
        AppUser appUser = new AppUser();

        appUser.setEmail(userDto.getEmail());
        appUser.setName(userDto.getName());
        appUser.setSurname(userDto.getSurname());
        appUser.setCreatedAt(userDto.getCreatedAt());
        appUser.setBlacklisted(userDto.getBlacklisted());
        appUser.setActive(userDto.getActive());

        return userRepository.save(appUser);
    }
}
