package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.model.AppUser;

public interface UserService {
    AppUser createUser(UserDto userDto);

    AppUser addRoleToUser(UserDto userDto);

    AppUser getUserById(int id);

    void deleteUserById(int id);

//    AppUser getAdminByRole(String roleType);

    AppUser getAdminByRoleType(String roleType);

    AppUser getAdminRoleType(String roleType);

    AppUser updateUserById(int id, UpdateUserDto updateUserDto);

}
