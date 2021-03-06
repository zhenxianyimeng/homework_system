package com.system.service;

import com.system.common.info.LoginInfo;
import com.system.common.info.StudentInfo;
import com.system.common.info.TeacherInfo;
import com.system.entity.Answer;
import com.system.entity.Student;
import com.system.entity.Teacher;
import com.system.entity.TeacherCommit;
import com.system.repository.AnswerRepository;
import com.system.repository.TeacherCommitRepository;
import com.system.repository.TeacherRepository;
import com.system.vo.request.SelectRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zjb
 * @date 2018/7/8.
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherCommitRepository teacherCommitRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public void saveScore(Long answerId, Double score){
        Answer answer = answerRepository.findFirstByIdEquals(answerId);
        answer.setScore(score);
        answerRepository.save(answer);
    }

    public void saveTeacher(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public List<Answer> findAnswerByQuestionId(Long questionId){
        return answerRepository.findAllByQuestionIdEquals(questionId);
    }

    public Answer findAnswerById(Long answerId){
        return answerRepository.findFirstByIdEquals(answerId);
    }

    public Teacher findByNameAndPwd(String name, String password){
        return teacherRepository.findFirstByNameEqualsAndPasswordEquals(name, password);
    }

    public boolean isTokenValid(String token){
        return teacherRepository.findFirstByTokenEquals(token)==null ? false : true;
    }

    public Teacher findByToken(String token){
        return teacherRepository.findFirstByTokenEquals(token);
    }

    public void  saveCheckAnswer(Long answerId, String file, String assessment){
        Answer answer = answerRepository.findFirstByIdEquals(answerId);
        if(StringUtils.isEmpty(answer.getCheckUrl())){
            answer.setCheckUrl(file);
        }else {
            answer.setCheckUrl(answer.getCheckUrl()+","+file);
        }
        answer.setAssessment(assessment);
        answerRepository.save(answer);
    }
//    public void saveAnswer(Long questionId){
//        List<String> temp = StudentInfo.imageInfos.get(LoginInfo.TEACHER_TOKEN.get());
//        String url = "";
//        if(CollectionUtils.isNotEmpty(temp)){
//            url = String.join(",", temp);
//        }
//        Student student = studentRepository.findFirstByTokenEquals(LoginInfo.STUDENT_TOKEN.get());
//        Answer answer = new Answer();
//        answer.setQuestionId(questionId);
//        answer.setStudentId(student.getId());
//        answer.setUrl(url);
//        answer.setStatus(0);
//        answerRepository.save(answer);
//        //teacherCommitRepository.save(teacherCommit);
//
//    }

    public void saveQuestion(SelectRequest request){
        List<String> temp = TeacherInfo.imageInfos.get(LoginInfo.TEACHER_TOKEN.get());
        TeacherInfo.imageInfos.put(LoginInfo.TEACHER_TOKEN.get(), new LinkedList<>());
        String url = "";
        if(CollectionUtils.isNotEmpty(temp)){
            url = String.join(",", temp);
        }
        TeacherCommit teacherCommit = new TeacherCommit(0, request.getTypeValue(), request.getGradeValue()
                                      ,request.getCourseValue(), request.getChapterValue(), request.getSectionValue(),request.getQuestionTitle()
                                      ,request.getMaxScore(),url, request.getQuestionNum());
        teacherCommitRepository.save(teacherCommit);

    }

    public List<TeacherCommit> findAllCommit(){
        return teacherCommitRepository.findAll();
    }
}
