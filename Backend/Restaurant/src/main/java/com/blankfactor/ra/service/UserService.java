package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;

public interface UserService {
    AppUser saveUser(UserDto userDto);

    AppUser getUserById(int id);
}
