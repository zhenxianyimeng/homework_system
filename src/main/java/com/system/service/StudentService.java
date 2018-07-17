package com.system.service;

import com.system.common.info.LoginInfo;
import com.system.common.info.StudentInfo;
import com.system.common.info.TeacherInfo;
import com.system.entity.Answer;
import com.system.entity.Student;
import com.system.entity.TeacherCommit;
import com.system.repository.AnswerRepository;
import com.system.repository.StudentRepository;
import com.system.repository.TeacherCommitRepository;
import com.system.vo.request.SelectRequest;
import org.apache.commons.collections4.CollectionUtils;
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
    private AnswerRepository answerRepository;

    @Autowired
    private TeacherCommitRepository teacherCommitRepository;

    public String findNameById(Long id){
        return studentRepository.findFirstByIdEquals(id).getName();
    }

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

    public void saveAnswer(Long questionId){
        List<String> temp = StudentInfo.imageInfos.get(LoginInfo.TEACHER_TOKEN.get());
        String url = "";
        if(CollectionUtils.isNotEmpty(temp)){
            url = String.join(",", temp);
        }
        Student student = studentRepository.findFirstByTokenEquals(LoginInfo.STUDENT_TOKEN.get());
        Answer answer = new Answer();
        answer.setQuestionId(questionId);
        answer.setStudentId(student.getId());
        answer.setUrl(url);
        answer.setStatus(0);
        answerRepository.save(answer);
        //teacherCommitRepository.save(teacherCommit);

    }
}
