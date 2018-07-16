package com.system.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.system.common.info.LoginInfo;
import com.system.common.info.StudentInfo;
import com.system.common.info.TeacherInfo;
import com.system.entity.TeacherCommit;
import com.system.service.StudentService;
import com.system.entity.Student;
import com.system.interceptor.StudentLogInterceptor;
import com.system.vo.request.SelectRequest;
import com.system.vo.response.QuestionVo;
import com.system.vo.response.Result;
import org.springframework.beans.BeanUtils;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    public final String BASE_DIR = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/";

    @GetMapping("/student/loginOut")
    public Result getIndex(HttpServletResponse httpServletResponse) {
        try {
            Cookie cookie = new Cookie(StudentLogInterceptor.TOKEN_NAME,"");//创建新cookie
            cookie.setMaxAge(0);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.fail();
    }

    @GetMapping("/student/check_token")
    @ResponseBody
    public Result checkToken(){
        return Result.success();
    }

    @GetMapping("/student/ask/id")
    @ResponseBody
    public Result findCommitById(@RequestParam Long id){
        try {
            TeacherCommit teacherCommit = studentService.findCommitById(id);
            QuestionVo questionVo = new QuestionVo();
            BeanUtils.copyProperties(teacherCommit, questionVo);
            String url = teacherCommit.getUrl();
            if(!StringUtils.isEmpty(url)){
                List<String> list = Arrays.asList(url.split(",")).stream().map(s-> "../"+s).collect(Collectors.toList());
                questionVo.setUrlList(list);
            }
            return Result.success(questionVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

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

    @GetMapping("/student/answer/submit")
    @ResponseBody
    public Result submit(@RequestParam String questionId){
        try {
            studentService.saveAnswer(Long.valueOf(questionId));
//            teacherService.saveQuestion();
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }


    @PostMapping("/student/answer/uploader")
    @ResponseBody
    public Result answerUploade(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        BufferedOutputStream out =  null;
        try {
            if (!file.isEmpty()) {
                String saveFileName ="answer"+ System.currentTimeMillis()+file.getOriginalFilename();
                String url = BASE_DIR + saveFileName;
                File saveFile = new File(url);
                out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                StudentInfo.put(LoginInfo.TEACHER_TOKEN.get(), saveFileName);
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

    @GetMapping("/student/ask/list")
    public Result listQuestion(){
        try {
            return Result.success(studentService.findAllTeacherCommit());
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }
}
