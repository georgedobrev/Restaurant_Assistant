package com.blankfactor.ra.unit.user;

import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void itShouldCheckIfAppUserExistsByEmail() {
        AppUser appUser = AppUser.builder()
                .email("email@gmail.com")
                .password("password")
                .name("TestUserName")
                .surname("TestUserSurname")
                .build();

        underTest.save(appUser);

        Optional<AppUser> appUserByEmail = underTest.findAppUserByEmail("email@gmail.com");

        assertTrue(appUserByEmail.isPresent(), "The AppUser should be present");
    }
}