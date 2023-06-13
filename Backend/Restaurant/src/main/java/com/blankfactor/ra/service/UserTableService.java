package com.blankfactor.ra.service;

import com.blankfactor.ra.model.AppTable;
import com.blankfactor.ra.model.AppUser;

public interface UserTableService {
    boolean isAppUserSeated(AppUser user, AppTable appTable);
}
