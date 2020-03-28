package com.group3.backend.service.taskService;


import com.group3.backend.model.Course;
import com.group3.backend.model.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskRowMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int arg1) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        Task task = new Task();
        task.setId(Integer.parseInt(rs.getString("taskId")));
        task.setDescription(rs.getString("taskDescription"));
        task.setDeadline(LocalDate.parse(rs.getString("taskDeadline"),formatter));
        return task;
    }
}

