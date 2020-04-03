package com.group3.backend.repository;

import com.group3.backend.model.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository {
    Course findById(int id);
}
