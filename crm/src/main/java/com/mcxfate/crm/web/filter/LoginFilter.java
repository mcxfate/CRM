package com.mcxfate.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @ClassName LoginFilter
 * @Description TODO
 * @Author admin
 * @Date 2022/4/7 22:51
 **/
public class LoginFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String path = request.getServletPath();
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){

            filterChain.doFilter(request,response);

        }else{

            HttpSession session = request.getSession();
            if (session.getAttribute("user") == null){
                response.sendRedirect("/login.jsp");
            }
            filterChain.doFilter(request,response);

        }

    }
}
