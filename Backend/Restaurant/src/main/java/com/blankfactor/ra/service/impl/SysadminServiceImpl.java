package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SysadminDto;
import com.blankfactor.ra.exceptions.custom.SysadminException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.repository.SysadminRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.service.SysadminService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SysadminServiceImpl implements SysadminService {
    private final SysadminRepository sysadminRepository;
    private final UserRepository userRepository;

    @Override
    public Sysadmin createSysadmin(SysadminDto sysadminDto) {
        Sysadmin sysadmin = Sysadmin.builder()
                .name(sysadminDto.getName())
                .surname(sysadminDto.getSurname())
                .email(sysadminDto.getEmail())
                .build();

        AppUser appUser = AppUser.builder()
                .name(sysadminDto.getName())
                .surname(sysadminDto.getName())
                .email(sysadminDto.getEmail())
                .build();

        userRepository.save(appUser);
        return sysadminRepository.save(sysadmin);
    }

    @Override
    public Sysadmin getSysadminById(Integer sysadminId) {
        return sysadminRepository.findById(sysadminId)
                .orElseThrow(() -> new SysadminException("Sysadmin not found with id " + sysadminId));
    }

    @Override
    public List<Sysadmin> getAllSysadmins() {
        return sysadminRepository.findAll();
    }

    @Override
    public Sysadmin updateSysadminById(Integer sysadminId, SysadminDto sysadminDto) {
        Sysadmin sysadmin = sysadminRepository.findById(sysadminId)
                .orElseThrow(() -> new SysadminException("Sysadmin" + sysadminId + "not found."));

        sysadmin.setEmail(sysadminDto.getEmail());
        sysadmin.setName(sysadminDto.getName());
        sysadmin.setSurname(sysadminDto.getSurname());
        sysadmin.setActive(!sysadmin.getActive());

        return sysadminRepository.save(sysadmin);
    }
}