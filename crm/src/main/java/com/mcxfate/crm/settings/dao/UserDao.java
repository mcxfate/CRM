package com.mcxfate.crm.settings.dao;

import com.mcxfate.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {

    User login(Map<String,String> map);

}
