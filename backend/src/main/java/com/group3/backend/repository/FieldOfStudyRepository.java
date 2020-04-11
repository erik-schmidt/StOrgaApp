package com.group3.backend.repository;

import com.group3.backend.model.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Integer> {
    FieldOfStudy findById(int id);
}
