package com.mcxfate.crm.settings.web.controller;

import com.mcxfate.crm.exception.LoginException;
import com.mcxfate.crm.settings.domain.User;
import com.mcxfate.crm.settings.service.UserService;
import com.mcxfate.crm.settings.service.impl.UserServiceImpl;
import com.mcxfate.crm.utils.MD5Util;
import com.mcxfate.crm.utils.PrintJson;
import com.mcxfate.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


/**
 * @ClassName UserController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/5 13:53
 **/
public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入到用户控制器");
        //获取访问路径
        String path = request.getServletPath();
        System.out.println(path);

        //判断用户访问的服务
        if ("/settings/user/login.do".equals(path)){

            login(request,response);

        }else if("/settings/user/xxx.do".equals(path)){

            //xxx(request,response);
        }


    }

    //用户登录控制
    private void login(HttpServletRequest request, HttpServletResponse response){

        System.out.println("进入到验证用户登录操作");
        //获取账号
        String loginAct = request.getParameter("username");
        //获取密码
        String loginPwd = request.getParameter("pwd");
        //获取用户浏览器端的ip地址
        String ip = request.getRemoteAddr();
        System.out.println("ip-------------:"+ip);
        //将密码的明文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);

        //创建业务层对象
        //未来业务层开发，统一使用代理类形态的接口对象
        //UserService userService = new UserServiceImpl();
        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try {
            User user = new User();
            user = userService.login(loginAct,loginPwd,ip);
            //创建会话连接对象,并将user对象存入session作用域中
            request.getSession().setAttribute("user",user);

            //如果程序执行到此处，说明业务层没有为controller抛出任何的异常
            //表示登录成功
            PrintJson.printJsonFlag(response,true);
        } catch (LoginException e) {
            e.printStackTrace();

            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败，为controller抛出了异常
            //表示登录失败
            /*

                {"success":true,"msg":?}

             */
            String msg = e.getMessage();
            /*

                我们现在作为contoller，需要为ajax请求提供多项信息

                可以有两种手段来处理：
                    （1）将多项信息打包成为map，将map解析为json串
                    （2）创建一个Vo
                            private boolean success;
                            private String msg;

                    如果对于展现的信息将来还会大量的使用，我们创建一个vo类，使用方便
                    如果对于展现的信息只有在这个需求中能够使用，我们使用map就可以了
             */
            HashMap<String, Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }


    }
}
