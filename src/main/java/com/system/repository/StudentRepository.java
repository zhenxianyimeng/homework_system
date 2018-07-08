package com.system.repository;

import com.system.entity.Student;
import com.system.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zjb
 * @date 2018/7/8.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findFirstByNameEqualsAndPasswordEquals(String name, String password);

    Student findFirstByTokenEquals(String token);
}
