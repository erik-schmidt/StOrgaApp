package com.group3.backend.repository;

import com.group3.backend.model.InitDataCourse;
import com.group3.backend.model.InitDataFieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitDataCourseRepository extends JpaRepository<InitDataCourse, Integer> {
    InitDataCourse findById(int id);
}