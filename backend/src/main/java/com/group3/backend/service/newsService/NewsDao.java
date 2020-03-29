package com.group3.backend.service.newsService;

import com.group3.backend.model.News;

import java.util.List;

public interface NewsDao {

    List<News> findAll();
    void insertNews(News news);
    void updateNews(News news);
    void executeUpdateNews(News news);
    void deleteNews(News news);
}
