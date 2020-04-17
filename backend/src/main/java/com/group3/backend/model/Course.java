package com.group3.backend.model;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
// TODO: 15.04.2020 Course im Student nur als String und Note speichern?  
public class Course {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    @Min(1)
    @Max(5)
    private double grade;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Course() {
    }

    public Course(String description, double grade, Student student){
        this.description = description;
        this.grade = grade;
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
