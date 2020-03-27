package com.group3.backend.service;


import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.util.HashMap;

import java.util.List;

import java.util.Map;

import com.group3.backend.model.Student;
import com.group3.backend.service.studentService.StudentDao;
import org.springframework.dao.DataAccessException;

import org.springframework.jdbc.core.PreparedStatementCallback;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;

import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;

@Repository

public class EmployeeDaoImpl implements StudentDao {

    public EmployeeDaoImpl(NamedParameterJdbcTemplate template) {

        this.template = template;

    }

    NamedParameterJdbcTemplate template;

    @Override

    public List<Student> findAll() {

        return template.query("select * from employee", new EmployeeRowMapper());

    }

    @Override

    public void insertEmployee(Student emp) {

        final String sql = "insert into employee(employeeId, employeeName , employeeAddress,employeeEmail) values(:employeeId,:employeeName,:employeeEmail,:employeeAddress)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()

                .addValue("employeeId", emp.getMatrNr())

                .addValue("employeeName", emp.getStudentPrename())

                .addValue("employeeEmail", emp.getEmployeeEmail())

                .addValue("employeeAddress", emp.getEmployeeAddress());

        template.update(sql,param, holder);

    }

    @Override

    public void updateEmployee(Student emp) {

        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()

                .addValue("employeeId", emp.getMatrNr())

                .addValue("employeeName", emp.getStudentPrename())

                .addValue("employeeEmail", emp.getEmployeeEmail())

                .addValue("employeeAddress", emp.getEmployeeAddress());

        template.update(sql,param, holder);

    }

    @Override

    public void executeUpdateEmployee(Student emp) {

        final String sql = "update employee set employeeName=:employeeName, employeeAddress=:employeeAddress, employeeEmail=:employeeEmail where employeeId=:employeeId";

        Map<String,Object> map=new HashMap<String,Object>();

        map.put("employeeId", emp.getMatrNr());

        map.put("employeeName", emp.getStudentPrename());

        map.put("employeeEmail", emp.getEmployeeEmail());

        map.put("employeeAddress", emp.getEmployeeAddress());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override

            public Object doInPreparedStatement(PreparedStatement ps)

                    throws SQLException, DataAccessException {

                return ps.executeUpdate();

            }

        });

    }

    @Override

    public void deleteEmployee(Student emp) {

        final String sql = "delete from employee where employeeId=:employeeId";

        Map<String,Object> map=new HashMap<String,Object>();

        map.put("employeeId", emp.getMatrNr());

        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override

            public Object doInPreparedStatement(PreparedStatement ps)

                    throws SQLException, DataAccessException {

                return ps.executeUpdate();

            }

        });

    }

}