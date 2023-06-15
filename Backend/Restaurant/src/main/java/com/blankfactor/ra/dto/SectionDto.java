package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    private String sectionName;
    private String waiterEmail;
    private List<AppTable> appTables;
}

