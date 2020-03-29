package com.group3.backend.service.newsService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.group3.backend.model.News;
import org.springframework.jdbc.core.RowMapper;

public class NewsRowMapper implements RowMapper<News> {

    @Override

    public News mapRow(ResultSet rs, int arg1) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy, hh:mm:ss");
        News news = new News();
        news.setId(Integer.parseInt(rs.getString("newsId")));
        news.setTitle(rs.getString("newsTitle"));
        news.setText(rs.getString("newsText"));
        news.setDateTime(LocalDateTime.parse(rs.getString("couresEcts"),formatter));
        news.setCreator(rs.getString("newsCreator"));
        return news;
    }

}