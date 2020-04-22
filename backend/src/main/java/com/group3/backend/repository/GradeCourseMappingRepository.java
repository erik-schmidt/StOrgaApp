package com.group3.backend.repository;

import com.group3.backend.model.GradeCourseMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeCourseMappingRepository extends JpaRepository<GradeCourseMapping, String> {
    GradeCourseMapping findByCourseNumber(String courseNumber);
}
