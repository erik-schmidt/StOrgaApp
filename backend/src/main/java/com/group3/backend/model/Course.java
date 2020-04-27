package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
// TODO: 15.04.2020 Course im Student nur als String und Note speichern?  
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fieldOfStudy;
    private String number;
    private String description;
    private String room;
    private String professor;
    private int ects;
    private String kindOfSubject;
    private int reccomendedSemester;
    private String studyFocus;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Courses_Students",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students = new HashSet<>();


    public Course() {
    }

    public Course(String fieldOfStudy, String number, String description, String room, String professor, int ects,
                  String kindOfSubject, int reccomendedSemester, String studyFocus){
        this.fieldOfStudy = fieldOfStudy;
        this.number = number;
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.kindOfSubject  = kindOfSubject;
        this.reccomendedSemester = reccomendedSemester;
        this.studyFocus = studyFocus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public String getKindOfSubject() {
        return kindOfSubject;
    }

    public void setKindOfSubject(String kindOfSubject) {
        this.kindOfSubject = kindOfSubject;
    }

    public int getReccomendedSemester() {
        return reccomendedSemester;
    }

    public void setReccomendedSemester(int reccomendedSemester) {
        this.reccomendedSemester = reccomendedSemester;
    }

    public String getStudyFocus() {
        return studyFocus;
    }

    public void setStudyFocus(String studyFocus) {
        this.studyFocus = studyFocus;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
}
