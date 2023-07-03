package com.blankfactor.ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppTableDto {
    private int tableNumber;
    private boolean occupied;
    private int capacity;
    private boolean virtualTable;
    private boolean active;
}