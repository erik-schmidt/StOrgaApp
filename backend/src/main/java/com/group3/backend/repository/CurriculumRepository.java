package com.group3.backend.repository;

import com.group3.backend.model.Course;
import com.group3.backend.model.Curriculum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {
    CurriculumRepository findById(int id);
}
