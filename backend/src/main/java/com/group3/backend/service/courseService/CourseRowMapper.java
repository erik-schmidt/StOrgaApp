package com.group3.backend.service.courseService;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.group3.backend.model.Course;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int arg1) throws SQLException {
        Course course = new Course();
        course.setId(rs.getString("courseId"));
        course.setDescription(rs.getString("courseDescription"));
        course.setRoom(rs.getString("courseRoom"));
        course.setProfessor(rs.getString("courseProfessor"));
        course.setEcts(Integer.parseInt(rs.getString("couresEcts")));
        course.setGrade(Double.parseDouble(rs.getString("couresGrade")));
        course.setFieldOfStudy(rs.getString("courseFieldOfStudy"));
        return course;
    }

}