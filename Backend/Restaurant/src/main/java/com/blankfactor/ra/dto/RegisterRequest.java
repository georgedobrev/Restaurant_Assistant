package com.blankfactor.ra.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String password;
}
