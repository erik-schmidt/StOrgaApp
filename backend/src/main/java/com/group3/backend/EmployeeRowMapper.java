package com.group3.backend;


import java.sql.ResultSet;

import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmployeeRowMapper implements RowMapper<Student> {

    @Override

    public Student mapRow(ResultSet rs, int arg1) throws SQLException {

        Student emp = new Student();

        emp.setMatrNr(rs.getString("employeeId"));

        emp.setStudentPrename(rs.getString("employeeName"));

        emp.setEmployeeEmail(rs.getString("employeeEmail"));

        return emp;

    }

}