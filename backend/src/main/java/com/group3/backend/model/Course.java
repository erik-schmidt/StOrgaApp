package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String fieldOfStudy;
    //The specific number of the course
    @NotNull
    private String number;
    //The name of the course
    @NotNull
    private String description;
    //The room the course takes place
    @NotNull
    private String room;
    //The professor who leads the course
    @NotNull
    private String professor;
    //The number of ECTs the course is worth
    @NotNull
    private int ects;
    //The kind of subject is one of the two: 1. Pflichtfach 2. Wahlfach
    @NotNull
    private String kindOfSubject;
    //The semester the course is recommended for
    @NotNull
    private int recommendedSemester;
    //The study focus is one of the three: 1. Mobile Computing 2. Psychologie 3. Allgemein
    @NotNull
    private String studyFocus;
    //The number of hours which are calculated to spend in class
    @NotNull
    private Double workingHoursInClass;
    //The number of hours which are calculated to spend for learning by your own
    @NotNull
    private Double workingHoursSelf;
    //The kind of exam is one of the two: 1. Note 2. Schein         //TODO: Ist das mit kindOfExam gemeint?
    @NotNull
    private String kindOfExam;
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
                  String kindOfSubject, int reccomendedSemester, String studyFocus, Double workingHoursInClass,
                  Double workingHoursSelf, String kindOfExam){
        this.fieldOfStudy = fieldOfStudy;
        this.number = number;
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.kindOfSubject  = kindOfSubject;
        this.recommendedSemester = recommendedSemester;
        this.studyFocus = studyFocus;
        this.workingHoursInClass = workingHoursInClass;
        this.workingHoursSelf = workingHoursSelf;
        this.kindOfExam = kindOfExam;
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

    public int getRecommendedSemester() {
        return recommendedSemester;
    }

    public void setRecommendedSemester(int reccomendedSemester) {
        this.recommendedSemester = reccomendedSemester;
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

    public Double getWorkingHoursInClass() {
        return workingHoursInClass;
    }

    public void setWorkingHoursInClass(Double workingHoursInClass) {
        this.workingHoursInClass = workingHoursInClass;
    }

    public Double getWorkingHoursSelf() {
        return workingHoursSelf;
    }

    public void setWorkingHoursSelf(Double workingHoursSelf) {
        this.workingHoursSelf = workingHoursSelf;
    }

    public String getKindOfExam() {
        return kindOfExam;
    }

    public void setKindOfExam(String kindOfExam) {
        this.kindOfExam = kindOfExam;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(fieldOfStudy + " ");
        sb.append(number + " ");
        sb.append(description + " ");
        sb.append(room + " ");
        sb.append(professor+ " ");
        sb.append("ECTS: " + ects + " ");
        sb.append(kindOfSubject + " ");
        sb.append("Recommended Semester: " + recommendedSemester + " ");
        sb.append(studyFocus);
        sb.append("Time in class: " + workingHoursInClass);
        sb.append("Self study Time: " + workingHoursSelf);
        sb.append("Exam: " + kindOfExam);
        return sb.toString();
    }
}
