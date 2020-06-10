package com.group3.backend.repository;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The repository for the {@link Course}.
 * Supported methods to find objects are:
 * - By description
 * - By number
 * - By studyFocus
 * - By kindOfSubject
 */

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Set<Course> findAllByDescription(String description);
    Course findByNumber(String number);
    List<Course> findAllByStudyFocus(String studyFocus);
    List<Course> findAllByKindOfSubject(String kindOfSubject);
    @Query("select max(id) from Course ")
    int findMaxID();
    Course findById(int id);
}
