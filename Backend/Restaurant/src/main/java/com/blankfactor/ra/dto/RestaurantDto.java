package com.blankfactor.ra.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    @Size(max = 100, message = "Name cannot be more than 100 characters")
    @NotNull(message = "Email cannot not be null")
    private String name;

    //@Formula(value = "(SELECT COUNT(*) FROM app_table WHERE restaurant_id = id)")    @Column(name = "tables_count")
    private Integer tablesCount;

    @Size(max = 50, message = "Address cannot be more than 50 characters")
    @NotNull(message = "Email cannot not be null")
    private String address;

    @Size(max = 12, message = "Number cannot be more than 12 characters")
    @NotNull(message = "Email cannot not be null")
    private String phoneNumber1;

    @Size(max = 12, message = "Number cannot be more than 12 characters")
    private String phoneNumber2;

    @Size(max = 12, message = "Number cannot be more than 12 characters")
    private String phoneNumber3;
    private Boolean active;
}
