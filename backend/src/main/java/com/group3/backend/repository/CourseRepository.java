package com.group3.backend.repository;

import com.group3.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    //Course findById(int id);
    //Course findByDescription(String description);
    Set<Course> findAllByDescription(String description);
    Course findByNumber(String number);
//    Set<Course> findByMatrNr(String matrNr, String courseID);
}
