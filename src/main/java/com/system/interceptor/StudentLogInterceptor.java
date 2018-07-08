package com.system.interceptor;

import com.system.common.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Component
public class StudentLogInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "system-student";

    @Autowired
    StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies =  request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_NAME.equals(cookie.getName())) {
                    if (studentService.isTokenValid(cookie.getValue())) {
                        return true;
                    }
                }
            }
        }
        response.setStatus(401);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
}
