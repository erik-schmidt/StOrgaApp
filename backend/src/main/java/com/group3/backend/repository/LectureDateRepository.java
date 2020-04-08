package com.group3.backend.repository;

import com.group3.backend.model.Course;
import com.group3.backend.model.LectureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureDateRepository extends JpaRepository<LectureDate, Integer> {
    LectureDate findById(int id);
}
