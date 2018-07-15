package com.system.repository;

import com.system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zjb
 * @date 2018/7/14.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
}
