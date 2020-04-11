package com.group3.backend.controller;

import com.group3.backend.LocalDateTimeConverter;
import com.group3.backend.model.News;
import com.group3.backend.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private NewsRepository newsRepository;

    @Autowired
    public NewsController(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @GetMapping("/get")
    public List<News> getAllStudents(){
        List<News> newsList = newsRepository.findAll();
        return newsList;
    }

    @PostMapping("post")
    public ResponseEntity<News> createNews(@RequestBody News news){
        News nw = new News(news.getTitle(), news.getText(), news.getCreator(), news.getDateTime());
        //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:MM:ss");
        //LocalDateTime dt = LocalDateTime.parse(news.getDateTime().toString(), dtf);
        //LocalDateTime hs = news.getDateTime();

        //LocalDateTimeConverter ldtc = new LocalDateTimeConverter();
        //Timestamp d = ldtc.convertToDatabaseColumn(news.getDateTime());
        //nw.setDateTime(hs);
        newsRepository.save(nw);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<News>  delteteNews(@PathVariable(value = "id") int id){
        News news = newsRepository.findById(id);
        newsRepository.delete(news);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
