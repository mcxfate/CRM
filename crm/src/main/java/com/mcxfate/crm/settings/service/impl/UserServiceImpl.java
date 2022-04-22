package com.mcxfate.crm.settings.service.impl;

import com.mcxfate.crm.exception.LoginException;
import com.mcxfate.crm.settings.dao.UserDao;
import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.settings.service.UserService;
import com.mcxfate.crm.utils.DateTimeUtil;
import com.mcxfate.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author admin
 * @Date 2022/4/5 15:32
 **/
public class UserServiceImpl implements UserService {

    private UserDao userDao = (UserDao) SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        //创建一个map集合存储用户账号和密码
        Map<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        //调用dao进行登录验证
        User user = userDao.login(map);
        if (user == null){
            throw new LoginException("账号或密码错误!");
        }

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime) < 0){
            throw new LoginException("账号已失效!");
        }

        //判断锁定状态
        String statue = user.getLockState();
        if ("0".equals(statue)){
            throw new LoginException("账号已锁定!");
        }

        boolean flag = user.getAllowIps().contains(ip);
        if(!flag){
            throw new LoginException("ip地址受限!");
        }

        return user;
    }
}
