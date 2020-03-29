package com.group3.backend.service.taskService;

import com.group3.backend.model.Task;
import com.group3.backend.service.GenericDao;
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
    public class TaskDaoImpl implements GenericDao<Task> {

        public TaskDaoImpl(NamedParameterJdbcTemplate template) {
            this.template = template;
        }
        NamedParameterJdbcTemplate template;

        @Override
        public List<Task> findAll() {
            return template.query("select * from task", new TaskRowMapper());
        }

        @Override
        public void insert(Task task) {
            final String sql = "insert into task(taskId, taskDescription, taskDeadline)" +
                    " values(:taskId,:taskDescription,:taskDeadline)";

            KeyHolder holder = new GeneratedKeyHolder();

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("taskId", task.getId())
                    .addValue("taskDescription", task.getDescription())
                    .addValue("taskDeadline", task.getDeadline());
            template.update(sql,param, holder);

        }

        @Override
        public void update(Task task) {
            final String sql = "update task set taskiId=:taskId, taskDescription=:taskDescription, taskDeadline=:taskDeadline" +
                    " where taskId=:taskId";

            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("taskId", task.getId())
                    .addValue("taskDescription", task.getDescription())
                    .addValue("taskDeadline", task.getDeadline());
            template.update(sql,param, holder);
        }

        @Override
        public void executeUpdate(Task task) {

            final String sql = "update task set taskId=:taskId, taskDescription=:taskDescription, taskDeadline=:taskDeadline" +
                    " where taskId:=taskId";

            Map<String,Object> map=new HashMap<String,Object>();
            map.put("taskId", task.getId());
            map.put("taskDescription", task.getDescription());
            map.put("taskDeadline", task.getDeadline());
            template.execute(sql,map,new PreparedStatementCallback<Object>() {

                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });
        }

        @Override
        public void delete(Task task) {
            final String sql = "delete from task where taskId=:taskId";
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("taskId", task.getId());
            template.execute(sql,map,new PreparedStatementCallback<Object>() {
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });
        }
}
