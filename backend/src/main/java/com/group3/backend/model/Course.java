package com.group3.backend.model;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    // TODO: 13.04.2020 field of Study change to object 
    private String fieldOfStudy;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // TODO: 01.04.2020 can save list or need Set
    private Set<LectureDate> lectureDateList;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Course() {
    }

    public Course(String description, String room, String professor, int ects, double grade, String fieldOfStudy,
                  Set<LectureDate> lectureDateList, Student student){
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.grade = grade;
        this.fieldOfStudy = fieldOfStudy;
        this.lectureDateList = lectureDateList;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
