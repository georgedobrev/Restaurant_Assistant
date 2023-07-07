package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.LoginRequestRoleType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoogleTokenDto {
    private String googleJwt;
    private LoginRequestRoleType loginRequestRoleType;
}