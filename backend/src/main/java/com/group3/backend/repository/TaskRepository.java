package com.group3.backend.repository;

import com.group3.backend.model.Student;
import com.group3.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findById(int id);
    Set<Task> findByStudent(Student student);
}
