package com.group3.backend.model;

import java.util.List;

public class CourseList {

    private String name;
    private List<Course> courseList;

    public CourseList(){

    }

    public CourseList(String name, List<Course> courseList) {
        this.name = name;
        this.courseList = courseList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
