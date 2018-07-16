package com.system.service;

import com.system.common.info.LoginInfo;
import com.system.entity.Student;
import com.system.entity.TeacherCommit;
import com.system.repository.StudentRepository;
import com.system.repository.TeacherCommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherCommitRepository teacherCommitRepository;

    public void saveStudent(Student student){
        studentRepository.save(student);
    }

    public Student findByNameAndPwd(String name, String password){
        return studentRepository.findFirstByNameEqualsAndPasswordEquals(name, password);
    }

    public boolean isTokenValid(String token){
        return studentRepository.findFirstByTokenEquals(token)==null ? false : true;
    }

    public List<TeacherCommit> findAllTeacherCommit(){
        List<TeacherCommit> list = new ArrayList<>();
        Student student = studentRepository.findFirstByTokenEquals(LoginInfo.STUDENT_TOKEN.get());
        list = teacherCommitRepository.findAllByGradeEquals(student.getGrade());
        return list;
    }

    public TeacherCommit findCommitById(Long id){
        return teacherCommitRepository.findFirstByIdEquals(id);
    }
}
