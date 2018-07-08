package com.system.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.system.common.service.TeacherService;
import com.system.entity.Teacher;
import com.system.interceptor.TeacherLogInterceptor;
import com.system.vo.request.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PostMapping("/user/teacher/login")
    @ResponseBody
    public Result loginIn(@RequestParam String name,
                          @RequestParam String password,
                          HttpServletResponse response){
        try {

            if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
                return Result.fail();
            }
            Teacher teacher =  teacherService.findByNameAndPwd(name, password);
            if(teacher != null){
                String pwd = name + password + System.currentTimeMillis();
                String md = Hashing.md5().newHasher().putString(pwd, Charsets.UTF_8).hash().toString();
                teacher.setToken(md);
                Cookie cookie = new Cookie(TeacherLogInterceptor.TOKEN_NAME,md);//创建新cookie
                cookie.setMaxAge(12 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }
}
