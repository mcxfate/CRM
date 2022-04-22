package com.mcxfate.crm.workbench.service.impl;

import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.utils.SqlSessionUtil;
import com.mcxfate.crm.workbench.dao.ActivityDao;
import com.mcxfate.crm.workbench.domain.Activity;
import com.mcxfate.crm.workbench.service.ActivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ActivityServiceImpl
 * @Description TODO
 * @Author admin
 * @Date 2022/4/10 11:02
 **/
public class ActivityServiceImpl implements ActivityService {

    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);

    @Override
    public List<User> getAllUser() {

        List<User> list = new ArrayList<>();
        list = activityDao.queryAllUser();
        return list;
    }

    @Override
    public int insertActivity(Activity activity) {

        int result = activityDao.insertActivity(activity);
        return result;
    }

}
