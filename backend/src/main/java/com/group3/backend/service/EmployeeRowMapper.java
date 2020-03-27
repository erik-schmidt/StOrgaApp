package com.group3.backend.service;


import java.sql.ResultSet;

import java.sql.SQLException;

import com.group3.backend.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Student> {

    @Override

    public Student mapRow(ResultSet rs, int arg1) throws SQLException {

        Student student = new Student();

        student.setMatrNr(rs.getString("employeeId"));

        student.setStudentPrename(rs.getString("employeeName"));


        return student;

    }

}