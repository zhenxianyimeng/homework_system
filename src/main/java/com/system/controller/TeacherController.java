package com.system.controller;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.system.common.info.LoginInfo;
import com.system.common.info.TeacherInfo;
import com.system.common.utils.SendEmail;
import com.system.entity.Answer;
import com.system.entity.Student;
import com.system.entity.TeacherCommit;
import com.system.interceptor.StudentLogInterceptor;
import com.system.repository.StudentRepository;
import com.system.service.SelectService;
import com.system.service.StudentService;
import com.system.service.TeacherService;
import com.system.entity.Teacher;
import com.system.interceptor.TeacherLogInterceptor;
import com.system.vo.request.SelectRequest;
import com.system.vo.response.AnswerVo;
import com.system.vo.response.Result;
import com.system.vo.response.SelectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    public final String BASE_DIR = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/";

    @PostMapping("/teacher/answer/score")
    public Result saveScore(@RequestParam String answerId, @RequestParam Double score){
        try {
            teacherService.saveScore(Long.valueOf(answerId), score);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @GetMapping("/teacher/answer/answerId")
    public Result findAnswerByAnswerId(@RequestParam String answerId){
        try {
            Answer answer = teacherService.findAnswerById(Long.valueOf(answerId));
            AnswerVo answerVo = new AnswerVo();
            BeanUtils.copyProperties(answer, answerVo);
            String url = answer.getUrl();
            if(!StringUtils.isEmpty(url)){
                List<String> urlList = Arrays.asList(url.split(",")).stream().map(s->"../"+s).collect(Collectors.toList());
                answerVo.setUrlList(urlList);
            }
            answerVo.setStudentName(studentService.findNameById(answerVo.getStudentId()));
            TeacherCommit commit = studentService.findCommitById(answerVo.getQuestionId());
            answerVo.setQuestionTitle(commit.getQuestionTitle());
            answerVo.setMaxScore(commit.getMaxScore());
            return Result.success(answerVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @GetMapping("/teacher/answer")
    public Result findAnswerByQuestionId(@RequestParam String questionId){
        try {
            List<Answer> list = teacherService.findAnswerByQuestionId(Long.valueOf(questionId));
            List<AnswerVo> reList = new LinkedList<>();
            if(!CollectionUtils.isEmpty(list)){
                for(Answer answer : list){
                    AnswerVo answerVo = new AnswerVo();
                    BeanUtils.copyProperties(answer, answerVo);
                    String url = answer.getUrl();
                    if(!StringUtils.isEmpty(url)){
                        List<String> urlList = Arrays.asList(url.split(",")).stream().map(s->"../"+s).collect(Collectors.toList());
                        answerVo.setUrlList(urlList);
                    }
                    answerVo.setStudentName(studentService.findNameById(answerVo.getStudentId()));
                    reList.add(answerVo);
                }
            }
            return Result.success(reList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

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
            List<Student> list = studentRepository.findAllByGradeEquals(request.getGradeValue());
            if(!CollectionUtils.isEmpty(list)){
                for(Student student : list){
//                    SendEmail.send(student.getEmail(), SendEmail.getRandNum()+SendEmail.CONTEXT, SendEmail.TITLE);
                }
            }
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

    @GetMapping("/teacher/answer/cache_url")
    @ResponseBody
    public Result getCheckCache(){
        try {
            String tmp = TeacherInfo.tokenUrlMap.get(LoginInfo.TEACHER_TOKEN.get());
            String url = tmp.split(",")[1];
            Long answerId = Long.valueOf(tmp.split(",")[0]);
            Map<String, Object> map = new HashMap<>();
            map.put("url", url);
            map.put("answerId", answerId);
            return Result.success(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @PostMapping("/teacher/answer/save_check")
    @ResponseBody
    public Result saveCheckInfo(Long answerId, String url){
        try {
            TeacherInfo.tokenUrlMap.put(LoginInfo.TEACHER_TOKEN.get(), answerId+","+url);
            System.out.println(TeacherInfo.tokenUrlMap.get(LoginInfo.TEACHER_TOKEN.get()));
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail();
    }

    @PostMapping("/teacher/answer/check_uploader")
    @ResponseBody
    public Result checkUploade(@RequestParam("file") String file,String assessment ,String fileName, @RequestParam("answerId") Long  answerId,HttpServletRequest request) {
        BufferedOutputStream out =  null;
        try {
            if (!file.isEmpty()) {
                file = file.split(",")[1];
                fileName = fileName.replace("../","");
                String saveFileName = "check" + fileName;
                String url = BASE_DIR + saveFileName;
                generateImage(file, url);
//                File saveFile = new File(url);
//                out = new BufferedOutputStream(new FileOutputStream(saveFile));
//                out.write(file.getBytes());
//                out.flush();
//                out.close();
//                TeacherInfo.put(LoginInfo.TEACHER_TOKEN.get(), saveFileName);
                teacherService.saveCheckAnswer(answerId, saveFileName, assessment);
                return Result.success();
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

    private static boolean generateImage(String imgStr, String fileName)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = fileName;//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
