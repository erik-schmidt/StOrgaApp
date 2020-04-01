package com.group3.backend.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface CurriculumRepository {
    CurriculumRepository findById(int id);
}
