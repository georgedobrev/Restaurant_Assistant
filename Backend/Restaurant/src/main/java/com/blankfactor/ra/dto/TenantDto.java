package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.Restaurant;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TenantDto {

    private String name;
    private String surname;
    private String email;
    private Restaurant restaurant;
    private boolean blacklisted;
    private boolean active;
    private Instant createdAt;
}
