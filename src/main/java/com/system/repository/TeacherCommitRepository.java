package com.system.repository;

import com.system.entity.TeacherCommit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * @author zjb
 * @date 2018/7/14.
 */
public interface TeacherCommitRepository extends JpaRepository<TeacherCommit, Long> {

    List<TeacherCommit> findAllByGradeEquals(String grade);

    TeacherCommit findFirstByIdEquals(Long id);
}
