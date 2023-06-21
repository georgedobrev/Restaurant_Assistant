package com.blankfactor.ra.unit.user;

import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.UserException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
import com.blankfactor.ra.service.UserService;
import com.blankfactor.ra.service.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    private UserService userService = new UserServiceImpl(userRepository, userRoleRepository);

    private AutoCloseable autoCloseable;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserServiceImpl(userRepository, userRoleRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void createUser() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("test@abv.bg");
        updateUserDto.setName("Test");
        updateUserDto.setSurname("TestTest");
        updateUserDto.setRestaurant(new Restaurant());
        updateUserDto.setRoleType(RoleType.ADMIN);

        AppUser appUser = underTest.createUser(updateUserDto);

        if (updateUserDto.getRoleType() == RoleType.ADMIN){
            underTest.assignUserRole(updateUserDto, appUser);
        }

        verify(userRepository).save(any(AppUser.class));
    }

    @Test
    public void addRoleToUser_ShouldAssignRoleToUser() {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("Happy");
        restaurantRepository.save(restaurant);

        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setName("John");
        updateUserDto.setSurname("Doe");
        updateUserDto.setEmail("johndoe@example.com");
        updateUserDto.setPassword("password");
        updateUserDto.setRestaurant(restaurant);
        updateUserDto.setRoleType(RoleType.ADMIN);

        AppUser user = new AppUser();
        user.setName(updateUserDto.getName());
        user.setSurname(updateUserDto.getSurname());
        user.setEmail(updateUserDto.getEmail());
        user.setPassword(updateUserDto.getPassword());

        UserRole expectedUserRole = new UserRole(user, restaurant, RoleType.ADMIN);
        expectedUserRole.setRoleType(updateUserDto.getRoleType());
        underTest.assignUserRole(updateUserDto, user);

        verify(userRoleRepository).save(expectedUserRole);
    }

    @Test
    void getUserById() {
        int id = 1;
        AppUser expectedUser = new AppUser();
        expectedUser.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        AppUser retrievedUser = underTest.getUserById(id);

        verify(userRepository).findById(id);

        assertNotNull(retrievedUser);
        assertEquals(expectedUser.getEmail(), retrievedUser.getEmail());
    }

    @Test
    void getUserByEmail() {
        String email = "test@abv.bg";
        AppUser expectedUser = new AppUser();
        expectedUser.setEmail(email);

        when(userRepository.findAppUserByEmail(email)).thenReturn(Optional.of(expectedUser));

        AppUser retrievedUser = underTest.getUserByEmail(email);

        verify(userRepository).findAppUserByEmail(email);

        assertNotNull(retrievedUser);
        assertEquals(expectedUser.getEmail(), retrievedUser.getEmail());
    }

    @Test
    void getUserByEmail_UserNotFound() {
        String email = "test@abv.bg";

        when(userRepository.findAppUserByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> underTest.getUserByEmail(email));

        verify(userRepository).findAppUserByEmail(email);
    }

    @Test
    void updateUserById() {
        int userId = 1;
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("test@abv.bg");
        updateUserDto.setName("Test");
        updateUserDto.setSurname("TestTest");
        updateUserDto.setRestaurant(new Restaurant());
        updateUserDto.setRoleType(RoleType.ADMIN);

        AppUser existingUser = new AppUser();
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));

        UserRole existingUserRole = new UserRole();
        when(userRoleRepository.findByAppUserAndRestaurantAndRoleType(existingUser, updateUserDto.getRestaurant(), updateUserDto.getRoleType()))
                .thenReturn(Optional.of(existingUserRole));

        AppUser updatedUser = underTest.updateUserById(userId, updateUserDto);

        verify(userRepository).findById(userId);
        verify(userRoleRepository).findByAppUserAndRestaurantAndRoleType(existingUser, updateUserDto.getRestaurant(), updateUserDto.getRoleType());
        verify(userRoleRepository).delete(existingUserRole);
        verify(userRepository).save(existingUser);
        verify(userRoleRepository).save(any(UserRole.class));

        assertEquals(existingUser, updatedUser);
    }

    @Test
    void deleteUserById() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("test@abv.bg");
        updateUserDto.setName("Test");
        updateUserDto.setSurname("TestTest");
        updateUserDto.setRestaurant(new Restaurant());
        updateUserDto.setRoleType(RoleType.ADMIN);

        AppUser appUser = underTest.createUser(updateUserDto);
        underTest.assignUserRole(updateUserDto, appUser);
        List<UserRole> userRoles = userRoleRepository.findByAppUser_Id(1);

        assertThrows(UserException.class, () -> underTest.deleteUserById(2));
    }

    @Test
    void deleteUserById_WithoutException() {
        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setEmail("test@abv.bg");
        updateUserDto.setName("Test");
        updateUserDto.setSurname("TestTest");
        updateUserDto.setRestaurant(new Restaurant());
        updateUserDto.setRoleType(RoleType.ADMIN);

        AppUser appUser = underTest.createUser(updateUserDto);
        underTest.assignUserRole(updateUserDto, appUser);
        List<UserRole> userRoles = userRoleRepository.findByAppUser_Id(1);

        userRoleRepository.deleteAll(userRoles);
        userRepository.deleteById(1);

        verify(userRoleRepository).deleteAll(userRoles);
        verify(userRepository).deleteById(1);

        List<UserRole> deletedUserRoles = userRoleRepository.findByAppUser_Id(1);
        assertTrue(deletedUserRoles.isEmpty());

        Optional<AppUser> deletedUser = userRepository.findById(1);
        assertFalse(deletedUser.isPresent());
    }
}