package com.group3.backend.service.courseService;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.group3.backend.model.Course;
import org.springframework.jdbc.core.RowMapper;

public class CourseRowMapper implements RowMapper<Course> {

    @Override

    public Course mapRow(ResultSet rs, int arg1) throws SQLException {
        Course course = new Course();
        course.setDescription(rs.getString("courseDescription"));
        course.setRoom(rs.getString("courseRoom"));
        course.setProfessor(rs.getString("courseProfessor"));
        course.setDescription(rs.getString("couresEcts"));
        //course.setLectureDateArrayList(rs.getString("courseLectureDateArrayList"));
        return course;
    }

}