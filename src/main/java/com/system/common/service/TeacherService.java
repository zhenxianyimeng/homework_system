package com.system.common.service;

import com.system.entity.Teacher;
import com.system.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public Teacher findByNameAndPwd(String name, String password){
        return teacherRepository.findFirstByNameEqualsAndPasswordEquals(name, password);
    }

    public boolean isTokenValid(String token){
        return teacherRepository.findFirstByTokenEquals(token)==null ? false : true;
    }
}
