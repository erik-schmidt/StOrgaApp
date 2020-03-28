package com.group3.backend.model;

public class Grade {

    //todo: Wie muss ich das Studentenobjekt in der Datenbank abspeichern?

    private String Id;
    private Student student;
    private double grade;
    private Course course;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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