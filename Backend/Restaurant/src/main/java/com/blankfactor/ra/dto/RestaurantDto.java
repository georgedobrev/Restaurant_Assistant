package com.blankfactor.ra.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private String name;
    private String address;

    @Size(min = 10, max = 20, message = "Number cannot be less than 10 and more than 20 characters")
    @NotNull(message = "Phone Number 1 cannot not be null")
    private String phoneNumber1;

    @Size(min = 10, max = 20, message = "Number cannot be less than 10 and more than 20 characters")
    private String phoneNumber2;

    @Size(min = 10, max = 20, message = "Number cannot be less than 10 and more than 20 characters")
    private String phoneNumber3;
}
