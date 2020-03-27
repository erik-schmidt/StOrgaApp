package com.group3.backend.service.courseService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group3.backend.model.Course;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao{

    public CourseDaoImpl(NamedParameterJdbcTemplate template) {

        this.template = template;

    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<Course> findAll() {

        return template.query("select * from course", new CourseRowMapper());

    }

    @Override
    public void insertCourse(Course course) {

        final String sql = "insert into course(courseDescription, courseRoom , courseProfessor,courseEcts)" +
                " values(:courseDescription,:courseRoom,:courseEcts)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("courseDescription", course.getDescription())
                .addValue("courseRoom", course.getRoom())
                .addValue("courseProfessor", course.getProfessor())
                .addValue("courseEcts", course.getEcts());
        //.addValue("courseLectureDateArrayList", course.getLectureDateArrayList());
        template.update(sql,param, holder);

    }

    @Override
    public void updateCourse(Course course) {

        final String sql = "update course set courseDescription=:courseDescription, courseRoom=:courseRoom, courseProfessor=:courseProfessor, courseEcts=:courseEcts" +
                " where courseDescription=:courseDescription";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("courseDescription", course.getDescription())
                .addValue("courseRoom", course.getRoom())
                .addValue("courseProfessor", course.getProfessor())
                .addValue("couresEcts", course.getEcts());
        // .addValue("courseLectureDateArrayList", course.getLectureDateArrayList());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdateCourse(Course course) {

        final String sql = "update course set courseDescription=:courseDescription, courseRoom=:courseRoom, courseProfessor=:courseProfessor, courseEcts=:couresEcts" +
                " where courseDescription=:courseDescription";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("courseDescription", course.getDescription());
        map.put("courseRoom", course.getRoom());
        map.put("courseProfessor", course.getProfessor());
        map.put("courseEcts", course.getEcts());
        //    map.put("courseLectureDateArrayList", course.getLectureDateArrayList());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();

            }
        });

    }

    @Override

    public void deleteCourse(Course course) {
        final String sql = "delete from course where courseDescription=:courseDescription";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("courseDescription", course.getDescription());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}

