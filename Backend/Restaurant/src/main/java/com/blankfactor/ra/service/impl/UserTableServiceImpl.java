package com.blankfactor.ra.service.impl;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;
import com.blankfactor.ra.model.UserTable;
import com.blankfactor.ra.repository.UserTableRepository;
import com.blankfactor.ra.service.UserTableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserTableServiceImpl implements UserTableService {
    private final UserTableRepository userTableRepository;

    @Override
    public boolean isAppUserSeated(AppUser user, AppTable appTable) {
        UserTable userTable = userTableRepository.findByAppUserIdAndAppTableId(user.getId(), appTable).orElse(null);
        if (userTable == null) {
            return false;
        }
        return userTable.getEndTime() == null;
    }
}
