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
    @NotNull
    private String number;
    @NotNull
    private String description;
    @NotNull
    private String room;
    @NotNull
    private String professor;
    @NotNull
    private int ects;
    @NotNull
    private String kindOfSubject;
    @NotNull
    private int reccomendedSemester;
    @NotNull
    private String studyFocus;
    @NotNull
    private Double workingHoursInClass;
    @NotNull
    private Double workingHoursSelf;
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
        this.reccomendedSemester = reccomendedSemester;
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
        sb.append("Reccomended Semester: " + reccomendedSemester + " ");
        sb.append(studyFocus);
        sb.append("Time in class: " + workingHoursInClass);
        sb.append("Self study Time: " + workingHoursSelf);
        sb.append("Exam: " + kindOfExam);
        return sb.toString();
    }
}
