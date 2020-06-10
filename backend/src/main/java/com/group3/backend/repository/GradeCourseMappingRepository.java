package com.group3.backend.repository;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * The repository for the {@link GradeCourseMapping}.
 * Supported methods to find objects are:
 * - By courseNumber
 * - By student
 * - By studentMatrNr
 */

@Repository
public interface GradeCourseMappingRepository extends JpaRepository<GradeCourseMapping, String> {
    GradeCourseMapping findByCourseNumber(String courseNumber);
    Set<GradeCourseMapping> findAllByStudent(Student student);
    Set<GradeCourseMapping> findAllByStudentMatrNr(String matrNr);
    @Query("select max(id) from GradeCourseMapping ")
    int findMaxID();
    GradeCourseMapping findById(int id);
}
