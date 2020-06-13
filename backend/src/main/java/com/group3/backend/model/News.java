package com.group3.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class News implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String title;
    @NotNull
    private String message;
    @NotNull
    private String author;
    @NotNull
    private LocalDate published;

    public News() {

    }

    public News(String title, String message, String author, LocalDate published){
        this.title = title;
        this.message = message;
        this.author = author;
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getPublished() {
        return published;
    }

    public void setPublished(LocalDate published) {
        this.published = published;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(title + " ");
        sb.append(message + " ");
        sb.append(author + " ");
        sb.append(published + " ");
        return sb.toString();
    }
}
