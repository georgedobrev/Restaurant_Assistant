package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.Restaurant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TenantDto {

    @Size(max = 100, message = "Name cannot be more than 100 characters")
    private String name;

    @Size(max = 100, message = "Surname cannot be more than 100 characters")
    private String surname;

    @Email(message = "Email is not valid", regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
    @Size(max = 100, message = "Email cannot be more than 100 characters")
    @NotNull(message = "Email cannot not be null")
    private String email;
    private Restaurant restaurant;
}
