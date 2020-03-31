package com.group3.backend.repository;

import com.group3.backend.model.Student;
import com.group3.backend.model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
    TaskList findById(int id);
}
