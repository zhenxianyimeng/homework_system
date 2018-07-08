package com.system.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Component
public class TeacherLogInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "system-teacher";



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return false;
    }
}
