package com.system.interceptor;

import com.system.common.info.LoginInfo;
import com.system.service.TeacherService;
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
public class TeacherLogInterceptor implements HandlerInterceptor {

    public static final String TOKEN_NAME = "system-teacher";

    @Autowired
    TeacherService teacherService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Cookie[] cookies =  request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (TOKEN_NAME.equals(cookie.getName())) {
                    if (teacherService.isTokenValid(cookie.getValue())) {
                        LoginInfo.TEACHER_TOKEN.set(cookie.getValue());
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

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LoginInfo.TEACHER_TOKEN.remove();
    }
}
