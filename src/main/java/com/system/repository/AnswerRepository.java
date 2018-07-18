package com.system.repository;

import com.system.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zjb
 * @date 2018/7/16.
 */
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionIdEquals(Long questionId);

    Answer findFirstByIdEquals(Long answerId);

    Answer findFirstByStudentIdEqualsAndQuestionIdEquals(Long studentId, Long questonId);
}
