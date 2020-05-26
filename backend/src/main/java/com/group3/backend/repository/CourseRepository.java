package com.group3.backend.repository;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    //Course findById(int id);
    //Course findByDescription(String description);
    Set<Course> findAllByDescription(String description);
    Course findByNumber(String number);
    List<Course> findAllByStudyFocus(String studyFocus);
    List<Course> findAllByKindOfSubject(String kindOfSubject);
//    Set<Course> findByMatrNr(String matrNr, String courseID);
}
