package com.blankfactor.ra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    private int sectionNumber;
    private String waiterEmail;
    private List<Integer> tableNumbers;
}

