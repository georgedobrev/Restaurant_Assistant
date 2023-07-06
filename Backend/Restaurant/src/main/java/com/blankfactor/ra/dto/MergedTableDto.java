package com.blankfactor.ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MergedTableDto {
    private List<Integer> tableNumbers;
}