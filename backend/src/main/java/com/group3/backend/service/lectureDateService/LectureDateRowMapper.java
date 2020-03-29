package com.group3.backend.service.lectureDateService;

import com.group3.backend.model.LectureDate;
import com.group3.backend.model.WeekDay;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LectureDateRowMapper implements RowMapper<LectureDate> {

    @Override

    public LectureDate mapRow(ResultSet rs, int arg1) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        LectureDate course = new LectureDate();
        course.setId(Integer.parseInt(rs.getString("lectureDateId")));
        course.setWeekDay(WeekDay.valueOf(rs.getString("lectureWeekDay")));
        course.setStartTime(LocalTime.parse(rs.getString("courseProfessor"),formatter));
        course.setFinishTime(LocalTime.parse(rs.getString("couresEcts"),formatter));
        return course;
    }
}