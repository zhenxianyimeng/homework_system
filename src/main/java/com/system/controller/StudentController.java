package com.system.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.system.common.service.StudentService;
import com.system.common.service.TeacherService;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.interceptor.StudentLogInterceptor;
import com.system.interceptor.TeacherLogInterceptor;
import com.system.vo.request.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/user/student/login")
    @ResponseBody
    public Result loginIn(@RequestParam String name,
                          @RequestParam String password,
                          HttpServletResponse response){
        try {

            if(StringUtils.isEmpty(name) || StringUtils.isEmpty(password)){
                return Result.fail();
            }
            Student student=  studentService.findByNameAndPwd(name, password);
            if(student != null){
                String pwd = name + password + System.currentTimeMillis();
                String md = Hashing.md5().newHasher().putString(pwd, Charsets.UTF_8).hash().toString();
                student.setToken(md);
                studentService.saveStudent(student);
                Cookie cookie = new Cookie(StudentLogInterceptor.TOKEN_NAME, md);//创建新cookie
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
