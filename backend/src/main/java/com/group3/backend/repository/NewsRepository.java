package com.group3.backend.repository;

import com.group3.backend.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    News findByTitle(String title);
    News findByMessage(String messageOrWord);
    List<News> findByPublishedDate(Date published);
}
