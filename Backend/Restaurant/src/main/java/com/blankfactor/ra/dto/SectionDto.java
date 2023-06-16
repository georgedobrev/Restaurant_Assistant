package com.blankfactor.ra.dto;

import com.blankfactor.ra.model.AppTable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    @Size(max = 255, message = "Section name cannot be more than 255 characters")
    private String sectionName;

    @NotNull(message = "AppTables cannot be null")
    @Valid
    private List<AppTable> appTables;
}

