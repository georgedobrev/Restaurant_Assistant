package com.blankfactor.ra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtTokenDto {
    private String jwtToken;
}