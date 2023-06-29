package com.blankfactor.ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private String name;
    private String address;
    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;
}
