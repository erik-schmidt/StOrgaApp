package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Model to represent an course.
 * The fieldOfStudy is the specific them of the {@link Course} (for example 'AIB').
 * The number is the specific number of the {@link Course}.
 * The description is the name of the {@link Course}.
 * The room is the room where the {@link Course} takes place.
 * The professor is the professor who leads the {@link Course}.
 * The ects are the number of ECTs the {@link Course} is worth.
 * The kindOfSubject is one of the two: 1. Pflichtfach 2. Wahlfach.
 * The recommendedSemester is the semester the {@link Course} is recommended for.
 * The studyFocus is one of the three: 1. Mobile Computing 2. Psychologie 3. Allgemein.
 * The workingHoursInClass are the number of hours which are calculated to spend in class.
 * The workingHoursSelf are the number of hours which are calculated to spend for learning by your own.
 * The kindOfExam is the kind of exam is one of the two: 1. Note 2. Schein //TODO: Ist das mit kindOfExam gemeint?
 */

@Entity
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
                                                        //TODO: What was that again?
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
    private Integer ects;
    @NotNull
    private String kindOfSubject;
    private Integer recommendedSemester;
    private String studyFocus;
    private Double workingHoursInClass;
    private Double workingHoursSelf;
    private String kindOfExam;
    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "Courses_Students",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private Set<Student> students = new HashSet<>();


    public Course() {
    }

    public Course(String fieldOfStudy, String number, String description, String room, String professor, int ects,
                  String kindOfSubject, int recommendedSemester, String studyFocus, Double workingHoursInClass,
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
