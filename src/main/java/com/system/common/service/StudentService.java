package com.system.common.service;

import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.repository.StudentRepository;
import com.system.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student findByNameAndPwd(String name, String password){
        return studentRepository.findFirstByNameEqualsAndPasswordEquals(name, password);
    }

    public boolean isTokenValid(String token){
        return studentRepository.findFirstByTokenEquals(token)==null ? false : true;
    }
}
