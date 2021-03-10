package com.study.employeemanagement.employeemanagement.config;

import com.study.employeemanagement.employeemanagement.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author best.lei
 * @date 2020/11/20 9:49 上午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/login","/login.html","/user/login","/assets/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
        registry.addViewController("/login.html").setViewName("/login");
        registry.addViewController("/dashboard.html").setViewName("/dashboard");
        registry.addViewController("/departments/add.html").setViewName("/departments/add");
    }
}
