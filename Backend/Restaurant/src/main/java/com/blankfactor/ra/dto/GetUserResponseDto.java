package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.model.Restaurant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(NON_NULL)
public class GetUserResponseDto {
    private String email;
    private String name;
    private String surname;
    private RoleType roleType;
    private Restaurant restaurant;
}
