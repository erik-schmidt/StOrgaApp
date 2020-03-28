package com.group3.backend.model;

public class Grade {

    //todo: Wie muss ich das Studentenobjekt in der Datenbank abspeichern?
    //todo: Braucht die Grade Klasse ein Studentenobjekt?

    private String id;
    private Student student;
    private double grade;
    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}