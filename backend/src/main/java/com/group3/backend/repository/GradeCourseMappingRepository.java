package com.group3.backend.repository;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface GradeCourseMappingRepository extends JpaRepository<GradeCourseMapping, String> {
    GradeCourseMapping findByCourseNumber(String courseNumber);
    Set<GradeCourseMapping> findAllByStudent(Student student);
    Set<GradeCourseMapping> findAllByStudentMatrNr(String matrNr);
}
