package com.blankfactor.ra.integration.user;

import com.blankfactor.ra.dto.AuthenticationResponse;
import com.blankfactor.ra.dto.UpdateUserDto;
import com.blankfactor.ra.dto.UserDto;
import com.blankfactor.ra.dto.UserEmailDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Restaurant;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.security.jwt.JwtService;
import com.blankfactor.ra.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GetUserTest {
    @LocalServerPort
    private int port;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private String token;

    @BeforeEach
    void setUp() {
        UserDto userDto = new UserDto();
        userDto.setName("Martin");
        userDto.setSurname("Kanev");
        userDto.setEmail("martin.kanev@blankfactor.com");
        userDto.setPassword("password");

        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/auth/register",
                userDto,
                AuthenticationResponse.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());

        AuthenticationResponse authenticationResponse = response.getBody();
        assertNotNull(authenticationResponse);

        String jwtToken = authenticationResponse.getToken();
        String refreshToken = authenticationResponse.getRefreshToken();
        assertNotNull(jwtToken);
        assertNotNull(refreshToken);
        assertFalse(jwtToken.isEmpty());
        assertFalse(refreshToken.isEmpty());

        token = jwtToken;

        Optional<AppUser> registeredUser = userRepository.findAppUserByEmail(userDto.getEmail());
        assertNotNull(registeredUser);
        assertEquals(userDto.getName(), registeredUser.get().getName());
        assertEquals(userDto.getSurname(), registeredUser.get().getSurname());
        assertEquals(userDto.getEmail(), registeredUser.get().getEmail());
        assertTrue(passwordEncoder.matches(userDto.getPassword(), registeredUser.get().getPassword()));

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Happy");
        restaurantRepository.save(restaurant);

        UpdateUserDto updateUserDto = new UpdateUserDto();
        updateUserDto.setName("Petko");
        updateUserDto.setSurname("Lyutskanov");
        updateUserDto.setEmail("petko@abv.bg");
        updateUserDto.setPassword("password123");
        updateUserDto.setRestaurant(restaurant);
        updateUserDto.setRoleType(RoleType.ADMIN);

        userService.createUser(updateUserDto);
    }

    //TODO: fix getUserByEmail test
    @Test
    public void getUserByEmail() {
        String testMapping = "/user";
        String email = "petko@abv.bg";
        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + token);

        UserEmailDto userEmailDto = new UserEmailDto();
        userEmailDto.setEmail(email);

        HttpEntity<UserEmailDto> requestEntity = new HttpEntity<>(userEmailDto, headers);

        ResponseEntity<AppUser> response = restTemplate.exchange(
                "http://localhost:" + port + testMapping,
                HttpMethod.GET,
                requestEntity,
                AppUser.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getUserById() {
        int userId = 1;
        String testMapping = "/user";

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<AppUser> response = restTemplate.exchange(
                "http://localhost:" + port + testMapping + "/" + userId,
                HttpMethod.GET,
                requestEntity,
                AppUser.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getId()).isEqualTo(userId);
    }

    @Test
    public void deleteUserById() {
        String testMapping = "/user";

        int userId = 2;

        HttpHeaders headers = new HttpHeaders();
        headers.set("authorization", "Bearer " + token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:" + port + testMapping + "/" + userId,
                HttpMethod.DELETE,
                requestEntity,
                Void.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Optional<AppUser> deletedUser = userRepository.findById(userId);
        assertFalse(deletedUser.isPresent());
    }
}