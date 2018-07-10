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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    public final String BASE_DIR = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"upload/";

    @PostMapping("/user/teacher/login")
    @ResponseBody
    public Result loginIn(@RequestParam String name,
                          @RequestParam String password,
                          HttpServletResponse response) {
        try {

            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(password)) {
                return Result.fail();
            }
            Teacher teacher = teacherService.findByNameAndPwd(name, password);
            if (teacher != null) {
                String pwd = name + password + System.currentTimeMillis();
                String md = Hashing.md5().newHasher().putString(pwd, Charsets.UTF_8).hash().toString();
                teacher.setToken(md);
                teacherService.saveTeacher(teacher);
                Cookie cookie = new Cookie(TeacherLogInterceptor.TOKEN_NAME, md);//创建新cookie
                cookie.setMaxAge(12 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
                return Result.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    @PostMapping("/teacher/ask/uploader")
    @ResponseBody
    public Result askUploade(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        BufferedOutputStream out =  null;
        try {
            if (!file.isEmpty()) {
                String saveFileName = file.getOriginalFilename();
                File saveFile = new File(BASE_DIR + saveFileName);
                out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.fail();
    }
}
