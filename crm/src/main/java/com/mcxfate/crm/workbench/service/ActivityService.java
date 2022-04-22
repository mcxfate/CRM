package com.mcxfate.crm.workbench.service;

import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.workbench.domain.Activity;

import java.util.List;

public interface ActivityService {

    List<User> getAllUser();

    int insertActivity(Activity activity);

}
