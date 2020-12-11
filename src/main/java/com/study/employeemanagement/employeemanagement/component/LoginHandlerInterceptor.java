package com.study.employeemanagement.employeemanagement.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/23 9:47 上午
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            request.setAttribute("msg","没有登入权限");
            request.getRequestDispatcher("/login.html").forward(request,response);
            return false;
        }
        return true;
    }
}
