package com.group3.backend.repository;

import com.group3.backend.model.Milestone;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository {
    Milestone findById(int id);
}
