package com.blankfactor.ra.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    @Size(max = 100, message = "Name cannot be more than 100 characters")
    @NotNull(message = "Name cannot not be null")
    private String name;

    private Integer tablesCount;

    @Size(max = 100, message = "Address cannot be more than 50 characters")
    @NotNull(message = "Address cannot not be null")
    private String address;

    @Size(min = 10, max = 20, message = "Number cannot be more than 20 characters and less than 10")
    @NotNull(message = "Phone Number 1 cannot not be null")
    private String phoneNumber1;

    @Size(min = 10, max = 20, message = "Number cannot be more than 20 characters and less than 10")
    private String phoneNumber2;

    @Size(min = 10, max = 20, message = "Number cannot be more than 13 characters and less than 10")
    private String phoneNumber3;
    private Boolean active;
}
