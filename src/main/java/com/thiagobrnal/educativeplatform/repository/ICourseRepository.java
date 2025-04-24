package com.thiagobrnal.educativeplatform.repository;

import com.thiagobrnal.educativeplatform.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
