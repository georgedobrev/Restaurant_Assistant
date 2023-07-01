package com.blankfactor.ra.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRestaurantDto {
    private RestaurantDto restaurantDto;
    private Integer userId;
}
