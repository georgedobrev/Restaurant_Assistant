package com.blankfactor.ra.dto;

import com.blankfactor.ra.enums.RequestType;
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

    @NotNull(message = "AppTable is required for notifications")
    private AppTable appTable;

    @NotNull(message = "AppUser is required for notifications")
    private AppUser appUser;

    @NotBlank(message = "Request type is required for notifications")
    private RequestType requestType;

    @Size(max = 50, message = "Name cannot be more than 50 characters")
    @NotBlank(message = "Message is required for notifications")
    private String message;
    private boolean approved;
    private Instant createdAt;
}
