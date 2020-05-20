package com.group3.backend.controller;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.model.News;
import com.group3.backend.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/news")
@CrossOrigin()
public class NewsController {

    private NewsService newsService;
    private AccessChecker accessChecker;

    @Autowired
    public NewsController(NewsService newsService, AccessChecker accessChecker){
        this.newsService = newsService;
        this.accessChecker = accessChecker;
    }

    /**
     * ping()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return newsService.ping();
    }

    /**
     * getAllNews
     * get all available news in the database
     * @return List<News>
     */
    @GetMapping("/get")
    public ResponseEntity<?> getAllNews(@RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return newsService.getAllNews();
    }

    /**
     * getNewsByPublishedDate
     * get all news available at the published date in the database
     * @param published Date
     * @return List<News>
     */
    @GetMapping("/get/{published}")
    public ResponseEntity<?> getNewsByPublishedDate(@PathVariable(value = "published") Date published){
        return newsService.getNewsByPublishedDate(published);
    }

    /**
     * getNewsByTitle
     * get news by search with the news title
     * @param title String
     * @return News
     */
    @GetMapping("/get/{title}")
    public ResponseEntity<?> getCourseByNumber(@PathVariable(value = "title") String title){
        return newsService.getNewsByTitle(title);
    }

    /**
     * getNewsByTitle
     * get news by search with the news message
     * @param message String
     * @return News
     */
    @GetMapping("/get/{message}")
    public ResponseEntity<?> getNewsByMessage(@PathVariable(value = "message") String message){
        return newsService.getNewsByMessage(message);
    }

    /**
     * creates new News (currently only Admin)
     * @param news
     * @param token
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createNews(@RequestBody News news, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return newsService.createNews(news);
    }

    /**
     * deletes a news object (identificates via title)
     * @param title
     * @param token
     * @return
     */
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<?> deleteNews(@PathVariable String title, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return newsService.deleteNews(title);
    }



}
