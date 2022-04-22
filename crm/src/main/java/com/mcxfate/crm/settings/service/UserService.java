package com.mcxfate.crm.settings.service;

import com.mcxfate.crm.exception.LoginException;
import com.mcxfate.crm.settings.domain.User;

public interface UserService {

    User login(String loginAct,String loginPwd,String ip) throws LoginException;


}
