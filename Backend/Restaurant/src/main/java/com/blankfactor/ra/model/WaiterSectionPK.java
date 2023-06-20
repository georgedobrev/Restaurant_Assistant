package com.blankfactor.ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WaiterSectionPK implements Serializable {

    // TODO To be tested apply User instead of Integer and apply to UserRolePK
    private static final Long serialVersionUID = 1L;
    private Section section;
    private AppUser waiter;
    private Shift shift;
}