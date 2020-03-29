package com.group3.backend.service.curriculumService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group3.backend.model.Curriculum;
import com.group3.backend.service.GenericDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CurriculumDaoImpl implements GenericDao<Curriculum> {

    public CurriculumDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<Curriculum> findAll() {
        return template.query("select * from curriculum", new CurriculumRowMapper());
    }

    @Override
    public void insert(Curriculum curriculum) {
        final String sql = "insert into curriculum(curriculumId, curriculumDescription , curriculumNotes, curriculumMilestone)" +
                " values(:curriculumId,:curriculumDescription,:curriculumNotes,:curriculumMilestone)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("curriculumId", curriculum.getId())
                .addValue("curriculumDescription", curriculum.getDescription())
                .addValue("curriculumNotes", curriculum.getNotes())
                .addValue("urriculumMilestone", curriculum.getMilestone());
        template.update(sql,param, holder);

    }

    @Override
    public void update(Curriculum curriculum) {

        final String sql = "update curriculum set curriculumId=:curriculumId, curriculumDescription=:curriculumDescription, " +
                "curriculumNotes=:curriculumNotes, curriculumMilestone=:curriculumMilestone" +
                " where curriculumId=:curriculumId";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("curriculumId", curriculum.getId())
                .addValue("curriculumDescription", curriculum.getDescription())
                .addValue("curriculumNotes", curriculum.getNotes())
                .addValue("urriculumMilestone", curriculum.getMilestone());
        template.update(sql,param, holder);

    }

    @Override
    public void executeUpdate(Curriculum curriculum) {
        final String sql = "update curriculum set curriculumId=:curriculumId, curriculumDescription=:curriculumDescription, " +
                "curriculumNotes=:curriculumNotes, curriculumMilestone=:curriculumMilestone" +
                " where curriculumId=:curriculumId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("curriculumId", curriculum.getId());
        map.put("curriculumDescription", curriculum.getDescription());
        map.put("curriculumNotes", curriculum.getNotes());
        map.put("urriculumMilestone", curriculum.getMilestone());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }

    @Override
    public void delete(Curriculum curriculum) {
        final String sql = "delete from curriculum where curriculumId=:curriculumId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("curriculumId", curriculum.getId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}

