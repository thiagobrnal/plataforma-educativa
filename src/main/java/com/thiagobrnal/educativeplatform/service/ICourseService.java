package com.thiagobrnal.educativeplatform.service;

import com.thiagobrnal.educativeplatform.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseService {

    //read
    public List<Course> findAll();

    //findById
    public Optional<Course> findById(Long id);

    //create
    public Course save(Course course);

    //update
    public Course updateCourse(Course course);

    //delete
    public void deleteById(Long id);
}
