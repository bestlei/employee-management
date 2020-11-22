package com.study.employeemanagement.employeemanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/8 8:55 上午
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    //@GetMapping("/dashboard")
    //public String dashboard(){
    //    return "dashboard";
    //}

    @PostMapping("/user/login")
    public String userLogin(@RequestParam("username") String username,
        @RequestParam("password") String password,
        Map<String, Object> error,
        HttpSession session
        ){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "dashboard";
        }else{
            error.put("msg","用户名密码错误");
            return "login";
        }

    }
}
