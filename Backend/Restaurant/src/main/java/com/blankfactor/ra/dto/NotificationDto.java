package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class NotificationDto {

    @NotNull(message = "AppTable is required")
    private AppTable appTable;

    @NotNull(message = "AppUser is required")
    private AppUser appUser;

    @NotBlank(message = "Request type is required")
    private String requestType;

    @Size(max = 500, message = "Name cannot be more than 500 characters")
    @NotBlank(message = "Message is required")
    private String message;

    private boolean approved;

    @NotNull(message = "Created at is required")
    private Instant createdAt;
}
