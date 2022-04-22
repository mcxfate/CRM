package com.mcxfate.crm.workbench.web.controller;

import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.settings.service.impl.UserServiceImpl;
import com.mcxfate.crm.utils.DateTimeUtil;
import com.mcxfate.crm.utils.PrintJson;
import com.mcxfate.crm.utils.ServiceFactory;
import com.mcxfate.crm.utils.UUIDUtil;
import com.mcxfate.crm.workbench.domain.Activity;
import com.mcxfate.crm.workbench.service.ActivityService;
import com.mcxfate.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ActivityController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/10 00:10
 **/
public class ActivityController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入市场活动控制器");
        String path = request.getServletPath();
        if ("/workbench/activity/getUserList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/activity/save.do".equals(path)){
            saveActivity(request,response);
        }





    }

    //获取所有用户
    private void getUserList(HttpServletRequest request, HttpServletResponse response){

        //活动服务对象
        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        //获取所有用户
        List<User> list = activityService.getAllUser();
        PrintJson.printJsonObj(response,list);

    }

    //添加活动记录
    private void saveActivity(HttpServletRequest request, HttpServletResponse response){

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate  = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String cost = request.getParameter("cost");
        String description = request.getParameter("description");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User)request.getSession().getAttribute("user")).getName();

        if (name == ""){
            PrintJson.printJsonFlag(response,false);
            return;
        }
        System.out.println(name);
        Activity activity = new Activity();
        activity.setId(id);
        activity.setOwner(owner);
        activity.setCost(cost);
        activity.setCreateBy(createBy);
        activity.setCreateTime(createTime);
        activity.setDescription(description);
        activity.setName(name);
        activity.setEndDate(endDate);
        activity.setStartDate(startDate);

        ActivityService activityService = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());

        int result = activityService.insertActivity(activity);

        if (result > 0){

            PrintJson.printJsonFlag(response,true);

        }else{
            PrintJson.printJsonFlag(response,false);
        }


    }
}
