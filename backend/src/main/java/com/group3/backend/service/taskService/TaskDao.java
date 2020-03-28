package com.group3.backend.service.taskService;

import com.group3.backend.model.Task;

import java.util.List;

public interface TaskDao {
    List<Task> findAll();
    void insertTask(Task task);
    void updateTask(Task task);
    void executeUpdateTask(Task task);
    void deleteTask(Task task);
}
