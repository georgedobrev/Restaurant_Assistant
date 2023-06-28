package com.blankfactor.ra.dto;

import lombok.Data;

@Data
public class UpdateTenantDto {
    private String oldEmail;
    private String email;
}
