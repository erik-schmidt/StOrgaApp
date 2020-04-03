package com.group3.backend.model;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String room;
    private String professor;
    @Min(1)
    @Max(50)
    private int ects;
    @Min(1)
    @Max(5)
    private double grade;
    private String fieldOfStudy;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    // TODO: 01.04.2020 can save list or need Set

    private Set<LectureDate> lectureDateList;

    public Course() {
    }

    public Course(String description, String room, String professor, int ects, double grade, String fieldOfStudy){
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.grade = grade;
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Set<LectureDate> getLectureDateList() {
        return lectureDateList;
    }

    public void setLectureDateList(Set<LectureDate> lectureDateList) {
        this.lectureDateList = lectureDateList;
    }
}
