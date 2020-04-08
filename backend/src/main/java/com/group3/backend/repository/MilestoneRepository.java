package com.group3.backend.repository;

import com.group3.backend.model.Course;
import com.group3.backend.model.Milestone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer> {
    Milestone findById(int id);
}
