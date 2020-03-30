package com.group3.backend.model;

import java.util.*;
import lombok.Data;

@Data
public class FieldOfStudy {

    private String id;
    private String description;
    private String name;
    private List<Course> courseIdList;
    private Integer semester;

    /*public FieldOfStudy() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourseArrayList() {
        return courseArrayList;
    }

    public void setCourseArrayList(ArrayList<Course> courseArrayList) {
        this.courseArrayList = courseArrayList;
    }*/
}