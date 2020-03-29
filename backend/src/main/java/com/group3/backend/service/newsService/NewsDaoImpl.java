package com.group3.backend.service.newsService;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.group3.backend.model.News;
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
public class NewsDaoImpl implements GenericDao<News> {

    public NewsDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<News> findAll() {
        return template.query("select * from news", new NewsRowMapper());
    }

    @Override
    public void insert(News news) {

        final String sql = "insert into news(newsId, newsTilte, newsText,newsDateTime, newsCreator)" +
                " values(:newsId,:newsTitle,:newsText,:newsDateTime,:newsCreator)";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("newsId", news.getId())
                .addValue("newsTitle", news.getTitle())
                .addValue("newsText", news.getText())
                .addValue("newsDateTime", news.getDateTime())
                .addValue("newsCreator", news.getCreator());
        template.update(sql,param, holder);
    }

    @Override
    public void update(News news) {

        final String sql = "update news set newsId=:newsId, newsTitle=:newsTitle, newsText=:newsText, newsDateTime=:newsDateTime, newsCreator=:newsCreator" +
                " where newsId=:newsId";

        KeyHolder holder = new GeneratedKeyHolder();

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("newsId", news.getId())
                .addValue("newsTitle", news.getTitle())
                .addValue("newsText", news.getText())
                .addValue("newsDateTime", news.getDateTime())
                .addValue("newsCreator", news.getCreator());
        template.update(sql,param, holder);
    }

    @Override
    public void executeUpdate(News news) {

        final String sql = "update news set newsId=:newsId, newsTitle=:newsTitle, newsText=:newsText, newsDateTime=:newsDateTime, newsCreator=:newsCreator" +
                " where newsId=:newsId";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("newsId", news.getId());
        map.put("newsTitle", news.getTitle());
        map.put("newsText", news.getText());
        map.put("newsDateTime", news.getDateTime());
        map.put("newsCreator", news.getCreator());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {

            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();

            }
        });
    }

    @Override
    public void delete(News news) {
        final String sql = "delete from news where newsId:=newsId";
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("newsId", news.getId());
        template.execute(sql,map,new PreparedStatementCallback<Object>() {
            @Override
            public Object doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                return ps.executeUpdate();
            }
        });
    }
}

