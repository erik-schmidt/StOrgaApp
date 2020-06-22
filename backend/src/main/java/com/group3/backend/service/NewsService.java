package com.group3.backend.service;

import com.group3.backend.model.Course;
import com.group3.backend.model.News;
import com.group3.backend.repository.NewsRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class NewsService {

    private NewsRepository newsRepository;
    private Logger logger = LoggerFactory.getLogger(NewsService.class);

    @Autowired
    public NewsService(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    /**
     * check if the system is reachable
     * @return String
     */
    public String ping() {
        return "reachable";
    }

    /**
     * get All news from the Database
     * @return ResponseEntity<List<News>> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getAllNews(){
        try {
            List<News> newsList = newsRepository.findAll();
            if (newsList.isEmpty()) {
                logger.error("Error while reading all news: There are no news saved");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no news saved");
            }
            return ResponseEntity.status(HttpStatus.OK).body(newsList);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * get a news by it's title
     * @param title
     * @return ResponseEntity<News> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getNewsByTitle(String title){
        try{
            News news = newsRepository.findByTitle(title);
            if(news == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no news with this title" + title +" in the system");
            }
            return ResponseEntity.status(HttpStatus.OK).body(title);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * get a news by message or word
     * @param message
     * @return ResponseEntity<News> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getNewsByMessage(String message){
        try{
            News news = newsRepository.findByMessage(message);
            if(news == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no news with this title" + message +" in the system");
            }
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /*
    /**
     * get a news by published Date
     * @param published
     * @return ResponseEntity<News> if successfull, otherwiese ResponseEntity<String> with error message
     */
    /*
    public ResponseEntity<?> getNewsByPublishedDate(Date published){
        try {
            List<News> newsList = newsRepository.findByPublishedDate(published);
            if (newsList.isEmpty()) {
                logger.error("Error while reading all news: There are no news saved");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no news saved");
            }
            return ResponseEntity.status(HttpStatus.OK).body(newsList);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }
    */

    /**
     * create a new news and save in db
     * @param news News
     * @return ResponseEntity<Course> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> createNews(News news){
        for(News n : newsRepository.findAll()){
            if(news.getTitle().equals(n.getTitle())||news.getMessage().equals(n.getMessage())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Newstitle or Message already exists in repository");
            }
        }
        try{
            News newNewsObject = new News(news.getTitle(), news.getMessage(), news.getUrlLink(), news.getAuthor(), news.getPublished());
            newsRepository.save(newNewsObject);
            return ResponseEntity.status(HttpStatus.OK).body(newNewsObject);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * delete news out of the repository
     * @param title String
     * @return
     */
    public ResponseEntity<?> deleteNews(String title){
        News news = newsRepository.findByTitle(title);
        if(news == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no course with this numbe r" +title+" in the system");
        }else {
            newsRepository.delete(news);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Deleted:" + news);
    }






}
