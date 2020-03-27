package com.group3.backend.service.courseService;

import com.group3.backend.model.Course;

import java.util.List;

public interface CourseDao {


    List<Course> findAll();
    void insertCourse(Course course);
    void updateCourse(Course course);
    void executeUpdateCourse(Course course);
    void deleteCourse(Course course);
}