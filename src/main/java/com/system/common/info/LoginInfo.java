package com.system.common.info;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zjb
 * @date 2018/7/8.
 */
public class LoginInfo {
    public static ThreadLocal<String> TEACHER_TOKEN = new ThreadLocal<>();

    public static ThreadLocal<String> STUDENT_TOKEN = new ThreadLocal<>();



}
