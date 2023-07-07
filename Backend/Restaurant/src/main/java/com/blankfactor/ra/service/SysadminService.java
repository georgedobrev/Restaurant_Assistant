package com.blankfactor.ra.service;

import com.blankfactor.ra.dto.SysadminDto;
import com.blankfactor.ra.exceptions.custom.SysadminException;
import com.blankfactor.ra.model.Sysadmin;

import java.util.List;

public interface SysadminService {
    Sysadmin createSysadmin(SysadminDto sysadminDto);

    Sysadmin getSysadminById(Integer sysadminId);

    List<Sysadmin> getAllSysadmins();

    Sysadmin updateSysadminById(Integer sysadminId, SysadminDto sysadminDto);
}