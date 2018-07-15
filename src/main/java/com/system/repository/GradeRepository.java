package com.system.repository;

import com.system.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * @author zjb
 * @date 2018/7/14.
 */
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
