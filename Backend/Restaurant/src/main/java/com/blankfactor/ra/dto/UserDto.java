package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String name;
    private String surname;
    private Instant createdAt;
    private Boolean blacklisted;
    private Boolean active;
}
