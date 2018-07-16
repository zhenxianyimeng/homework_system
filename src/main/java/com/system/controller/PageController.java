package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zjb
 * @date 2018/7/16.
 */
@Controller
public class PageController {
    @GetMapping("/t")
    public void getTeacherIndex(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect("/pages/teacher_index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/s")
    public void getStudentIndex(HttpServletResponse httpServletResponse) {
        try {
            httpServletResponse.sendRedirect("/pages/student_index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
