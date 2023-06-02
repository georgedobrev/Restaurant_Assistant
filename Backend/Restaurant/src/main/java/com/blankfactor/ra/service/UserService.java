package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.User;

public interface UserService {
    User saveUser(UserDto userDto);
}
