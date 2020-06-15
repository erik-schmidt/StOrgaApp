package com.group3.backend.model;

public class Homescreen {

    private String title;
    private String data;

    public Homescreen() {
    }

    public Homescreen(String title, String data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
