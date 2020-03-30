package com.group3.backend.service.gradeService;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import org.springframework.jdbc.core.RowMapper;

public class GradeRowMapper implements RowMapper<Grade> {

    @Override

    public Grade mapRow(ResultSet rs, int arg1) throws SQLException {
        Grade grade = new Grade();
        grade.setId(rs.getString("gradeId"));
        grade.setStudent((Student) rs.getObject("gradeStudent"));
        grade.setGrade(rs.getDouble("gradeGrade"));
        grade.setCourse((Course) rs.getObject("gradeCourse"));
        return grade;
    }

}