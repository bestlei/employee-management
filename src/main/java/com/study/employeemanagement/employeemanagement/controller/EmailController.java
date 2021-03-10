package com.study.employeemanagement.employeemanagement.controller;

import com.study.employeemanagement.employeemanagement.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 *
 * @author best.lei
 * @date 2021/3/8 9:40 上午
 */
@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * 发送邮件
     * @param emailAddress
     * @return
     */
    @GetMapping("/email/emp/{emailAddress}")
    public String sendEmpEmail(@PathVariable String emailAddress) {
        System.out.println("send email " + emailAddress);
        emailService.sendEmail("emp.direct",emailAddress,"this is a personal email");
        return "redirect:/emps";
    }

    @GetMapping("/email/department/{departmentName}")
    public String sendDeparmentEmail(@PathVariable String departmentName){
        System.out.println("send email " + departmentName);
        emailService.sendEmail("emp.fanout",departmentName,"this is a department email");
        return "redirect:/depts";
    }
}
