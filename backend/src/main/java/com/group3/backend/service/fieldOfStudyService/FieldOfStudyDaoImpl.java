package com.group3.backend.service.fieldOfStudyService;

import com.group3.backend.model.FieldOfStudy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



    @Repository
    public class FieldOfStudyDaoImpl implements FieldOfStudyDao {

        public FieldOfStudyDaoImpl(NamedParameterJdbcTemplate template) {

            this.template = template;

        }

        NamedParameterJdbcTemplate template;

        @Override
        public List<FieldOfStudy> findAll() {

            return template.query("select * from fieldOfStudy", new FieldOfStudyRowMapper());

        }

        @Override
        public void insertFieldOfStudy(FieldOfStudy fieldOfStudy) {

            final String sql = "insert into fieldOfStudy(fieldOfStudyId, fieldOfStudyDescription , fieldOfStudyName)" +
                    " values(:fieldOfStudyId,:fieldOfStudyDescription,:fieldOfStudyName)";

            KeyHolder holder = new GeneratedKeyHolder();

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("fieldOfStudyId", fieldOfStudy.getId())
                    .addValue("fieldOfStudyDescription", fieldOfStudy.getDescription())
                    .addValue("fieldOfStudyName", fieldOfStudy.getName());
            template.update(sql,param, holder);

        }

        @Override
        public void updateFieldOfStudy(FieldOfStudy fieldOfStudy) {

            final String sql = "update fieldOfStudy set fieldOfStudyId=:fieldOfStudyId, fieldOfStudyDescription=:fieldOfStudyDescription, fieldOfStudyName=:fieldOfStudyName" +
                    " where fieldOfStudyId=:fieldOfStudyId";

            KeyHolder holder = new GeneratedKeyHolder();

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("fieldOfStudyId", fieldOfStudy.getId())
                    .addValue("fieldOfStudyDescription", fieldOfStudy.getDescription())
                    .addValue("fieldOfStudyName", fieldOfStudy.getName());
            template.update(sql,param, holder);

        }

        @Override
        public void executeUpdateFieldOfStudy(FieldOfStudy fieldOfStudy) {

            final String sql = "update fieldOfStudy set fieldOfStudyId=:fieldOfStudyId, fieldOfStudyDescription=:fieldOfStudyDescription, fieldOfStudyName=:fieldOfStudyName" +
                    " where fieldOfStudyId=:fieldOfStudyId";

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("fieldOfStudyId", fieldOfStudy.getId());
            map.put("fieldOfStudyDescription", fieldOfStudy.getDescription());
            map.put("fieldOfStudyName", fieldOfStudy.getName());
            template.execute(sql,map,new PreparedStatementCallback<Object>() {

                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();

                }
            });

        }

        @Override

        public void deleteFieldOfStudy(FieldOfStudy fieldOfStudy) {
            final String sql = "delete from fieldOfStudy where fieldOfStudyId=:fieldOfStudyId";
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("fieldOfStudyId", fieldOfStudy.getId());
            template.execute(sql,map,new PreparedStatementCallback<Object>() {
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });
        }
}
