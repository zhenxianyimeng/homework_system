package com.system.config;

import com.system.interceptor.StudentLogInterceptor;
import com.system.interceptor.TeacherLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

    @Autowired
    TeacherLogInterceptor teacherLogInterceptor;

    @Autowired
    StudentLogInterceptor studentLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(teacherLogInterceptor).addPathPatterns("/teacher/**");
        registry.addInterceptor(studentLogInterceptor).addPathPatterns("/student/**");
    }
}
