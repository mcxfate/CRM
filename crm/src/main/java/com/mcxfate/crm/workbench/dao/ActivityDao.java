package com.mcxfate.crm.workbench.dao;

import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityDao {

    List<User> queryAllUser();

    int insertActivity(Activity activity);
}
