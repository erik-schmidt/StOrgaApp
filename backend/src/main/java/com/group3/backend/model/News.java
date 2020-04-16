package com.group3.backend.model;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class News implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String text;
    private String creator;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalDateTimeType")
    @Convert(disableConversion = true)
    //@Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dateTime;

    public News(){
    }

    public News(String title, String text, String creator, LocalDateTime dateTime){
        this.title = title;
        this.text = text;
        this.creator = creator;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
