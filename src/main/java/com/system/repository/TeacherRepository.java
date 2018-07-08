package com.system.repository;

import com.system.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zjb
 * @date 2018/7/8.
 */
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findFirstByNameEqualsAndPasswordEquals(String name, String password);

    Teacher findFirstByTokenEquals(String token);
}
