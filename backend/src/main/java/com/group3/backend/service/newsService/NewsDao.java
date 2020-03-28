package com.group3.backend.service.newsService;

import com.group3.backend.model.News;

import java.util.List;

public interface NewsDao {

    List<News> findAll();
    void insertCourse(News news);
    void updateCourse(News news);
    void executeUpdateCourse(News news);
    void deleteCourse(News news);
}
