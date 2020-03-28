package com.group3.backend.service.gradeService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group3.backend.model.Grade;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GradeDaoImpl implements GradeDao{

    public CourseDaoImpl(NamedParameterJdbcTemplate template) {

        this.template = template;

    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<Grade> findAll() {

        return template.query("select * from grade", new GradeRowMapper());

    }

    @Override
    public void insertGrade(Grade grade) {

        final String sql = "insert into grade(gradeId, gradeStudent, gradeGrade, gradeCourse)" +
                " values(:gradeStudent,:gradeGrade,:gradeCourse)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("gradeId", grade.getId())
                .addValue("gradeStudent", grade.getStudent())
                .addValue("gradeGrade", grade.getGrade())
                .addValue("gradeCourse", grade.getCourse());
        template.update(sql,param, holder);

    }

    @Override
    public void updateGrade(Grade grade) {

        final String sql = "update grade set gradeStudent=:gradeStudent, gradeGrade=:gradeGrade, gradeCourse=:gradeCourse" +
                " where gradeId=:gradeId";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("gradeStudent", grade.getStudent())
                .addValue("gradeGrade", grade.getGrade())
                .addValue("gradeCourse", grade.getCourse())
                .addValue("gradeId", grade.getId());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdateGrade(Grade grade) {

        final String sql = "update grade set gradeStudent=:gradeStudent, gradeGrade=:gradeGrade, gradeCourse=:gradeCourse" +
                " where gradeId=:gradeId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("gradeStudent", grade.getStudent());
        map.put("gradeGrade", grade.getGrade());
        map.put("gradeCourse", grade.getCourse());
        map.put("gradeId", grade.getId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();

            }
        });

    }

    @Override

    public void deleteGrade(Grade grade) {
        final String sql = "delete from grade where gradeId=:gradeId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("gradeId", grade.getId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}

