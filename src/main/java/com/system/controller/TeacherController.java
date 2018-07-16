package com.system.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.system.common.info.LoginInfo;
import com.system.common.info.TeacherInfo;
import com.system.interceptor.StudentLogInterceptor;
import com.system.service.SelectService;
import com.system.service.TeacherService;
import com.system.entity.Teacher;
import com.system.interceptor.TeacherLogInterceptor;
import com.system.vo.request.SelectRequest;
import com.system.vo.response.Result;
import com.system.vo.response.SelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    SelectService selectService;

    public final String BASE_DIR = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/";

    @GetMapping("/teacher/loginOut")
    public Result getIndex(HttpServletResponse httpServletResponse) {
        try {
            Cookie cookie = new Cookie(TeacherLogInterceptor.TOKEN_NAME,"");//创建新cookie
            cookie.setMaxAge(0);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    @GetMapping("/teacher/check_token")
    @ResponseBody
    public Result checkToken(){
        return Result.success();
    }

    @GetMapping("/teacher/ask/select_info")
    @ResponseBody
    public Result getSelectInfo(){
        try {
            SelectVo vo = selectService.getSelectOption();
            return Result.success(vo);
        }catch (Exception e){

        }
        return Result.fail();
    }

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

    @PostMapping("/teacher/ask/submit")
    @ResponseBody
    public Result submit(@RequestBody SelectRequest request){
        try {
//            System.out.println(request);
            teacherService.saveQuestion(request);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @GetMapping("/teacher/ask/list")
    public Result listQuestion(){
        try {
            return Result.success(teacherService.findAllCommit());
        }catch (Exception e){
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
                String saveFileName = "upload" + System.currentTimeMillis()+file.getOriginalFilename();
                String url = BASE_DIR + saveFileName;
                File saveFile = new File(url);
                out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                TeacherInfo.put(LoginInfo.TEACHER_TOKEN.get(), saveFileName);
                return Result.success(saveFileName);
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
