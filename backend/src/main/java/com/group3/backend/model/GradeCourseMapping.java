package com.group3.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GradeCourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String courseNumber;
    private double grade;

    public GradeCourseMapping() {
    }

    public GradeCourseMapping(String courseNumber, double grade) {
        this.courseNumber = courseNumber;
        this.grade = grade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(courseNumber + " ");
        sb.append(grade);
        return sb.toString();
    }
}
