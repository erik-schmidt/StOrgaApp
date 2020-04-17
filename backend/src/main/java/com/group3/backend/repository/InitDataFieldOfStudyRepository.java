package com.group3.backend.repository;

import com.group3.backend.model.InitDataFieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitDataFieldOfStudyRepository extends JpaRepository<InitDataFieldOfStudy, Integer> {
    InitDataFieldOfStudy findById(int id);
}