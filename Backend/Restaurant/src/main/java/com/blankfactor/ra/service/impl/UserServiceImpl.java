package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.User;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User saveUser(UserDto userDto) {
        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setBlacklisted(userDto.getBlacklisted());
        user.setActive(userDto.getActive());

        return userRepository.save(user);
    }
}
