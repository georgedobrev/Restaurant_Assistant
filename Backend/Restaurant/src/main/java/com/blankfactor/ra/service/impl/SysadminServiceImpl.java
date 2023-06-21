package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.dto.SysadminDto;
import com.blankfactor.ra.enums.RoleType;
import com.blankfactor.ra.exceptions.custom.SysadminException;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.Sysadmin;
import com.blankfactor.ra.model.UserRole;
import com.blankfactor.ra.repository.RestaurantRepository;
import com.blankfactor.ra.repository.SysadminRepository;
import com.blankfactor.ra.repository.UserRepository;
import com.blankfactor.ra.repository.UserRoleRepository;
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
    public Sysadmin getSysadminById(int sysadminId) throws SysadminException {
        return sysadminRepository.findById(sysadminId).orElseThrow(() -> new SysadminException("Sysadmin" + sysadminId + "not found."));
    }

    @Override
    public Sysadmin updateSysadminById(int sysadminId, SysadminDto sysadminDto) throws SysadminException {
        Sysadmin sysadmin = sysadminRepository.findById(sysadminId).orElseThrow(() -> new SysadminException("Sysadmin" + sysadminId + "not found."));

        sysadmin.setEmail(sysadminDto.getEmail());
        sysadmin.setName(sysadminDto.getName());
        sysadmin.setSurname(sysadminDto.getSurname());
        sysadmin.setActive(!sysadmin.getActive());

        return sysadminRepository.save(sysadmin);
    }

        @Override
    public List<Sysadmin> getAllSysadmins() {

        return sysadminRepository.findAll();
    }
    @Override
    public void deleteSysadminById(int sysadminId) {
        sysadminRepository.deleteById(sysadminId);
    }

    @Override
    public void deleteAll() {
        sysadminRepository.deleteAll();
    }

}
