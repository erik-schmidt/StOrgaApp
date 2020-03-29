package com.group3.backend.service.lectureDateService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group3.backend.model.LectureDate;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class LectureDateDaoImpl implements LectureDateDao {

    public LectureDateDaoImpl(NamedParameterJdbcTemplate template) {

        this.template = template;

    }

    NamedParameterJdbcTemplate template;

    @Override
    public List<LectureDate> findAll() {

        return template.query("select * from lectureDate", new LectureDateRowMapper());

    }

    @Override
    public void insertLectureDate(LectureDate lectureDate) {

        final String sql = "insert into lectureDate (lectureDateId, lectureDateWeekDay , lectureDateStartTime, lectureDateFinishTime)" +
                " values(:lectureDateId,:lectureDateWeekDay,:lectureDateStartTime,:lectureDateFinishTime)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("lectureDateId", lectureDate.getId())
                .addValue("lectureWeedDay", lectureDate.getWeekDay())
                .addValue("lectureStartTime", lectureDate.getStartTime())
                .addValue("lectureFinishTime", lectureDate.getFinishTime());
        template.update(sql,param, holder);

    }

    @Override
    public void updateLectureDate(LectureDate lectureDate) {

        final String sql = "update lectureDate set lectureDateId=:lectureDateId, lectureDateWeekDay=:lectureDateWeekDay," +
                " lectureDateStartTime=:lectureDateStartTime, lectureDateFinishTime=:lectureDateFinishTime" +
                " where lectureDateId=:lectureDateId";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("lectureDateId", lectureDate.getId())
                .addValue("lectureWeedDay", lectureDate.getWeekDay())
                .addValue("lectureStartTime", lectureDate.getStartTime())
                .addValue("lectureFinishTime", lectureDate.getFinishTime());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdateLectureDate(LectureDate lectureDate) {

        final String sql = "update lectureDate set lectureDateId=:lectureDateId, lectureDateWeekDay=:lectureDateWeekDay," +
                " lectureDateStartTime=:lectureDateStartTime, lectureDateFinishTime=:lectureDateFinishTime" +
                " where lectureDateId=:lectureDateId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("lectureDateId", lectureDate.getId());
        map.put("lectureDateWeekDay", lectureDate.getWeekDay());
        map.put("lectureDateStartTime", lectureDate.getStartTime());
        map.put("lectureDateFinishTiime", lectureDate.getFinishTime());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();

            }
        });

    }

    @Override
    public void deleteLectureDate(LectureDate lectureDate) {
        final String sql = "delete from lectureDate where lectureDateId=:lectureDateId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("lectureDateId", lectureDate.getId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}

