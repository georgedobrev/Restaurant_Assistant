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
    @Size(max = 50, message = "Section name cannot be more than 50 characters")
    private String sectionName;

    // TODO remove @NotNull, app tables could be assigned after the initialization of the Section class
    @NotNull(message = "AppTables cannot be null")
    @Valid
    private List<AppTable> appTables;
}