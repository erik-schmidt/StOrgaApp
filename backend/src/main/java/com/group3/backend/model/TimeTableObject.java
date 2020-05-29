package com.group3.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "timetable")
public class TimeTableObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    //todo aufsplitten in date und time, damit einfacher in der datenbank nach daten gesucht werden kann
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime startTimeDate;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime finishTimeDate;
    private String courseNumber;
    private String summary;
    private String UID;
    private String location;
    private String description;
    private String professor1;
    private String professor2;
    private String professor3;
    private String fieldOfStudyAndSemester;


    public TimeTableObject() {
    }

    public LocalDateTime getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(LocalDateTime startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public LocalDateTime getFinishTimeDate() {
        return finishTimeDate;
    }

    public void setFinishTimeDate(LocalDateTime finishTimeDate) {
        this.finishTimeDate = finishTimeDate;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfessor1() {
        return professor1;
    }

    public void setProfessor1(String professor1) {
        this.professor1 = professor1;
    }

    public String getProfessor2() {
        return professor2;
    }

    public void setProfessor2(String professor2) {
        this.professor2 = professor2;
    }

    public String getProfessor3() {
        return professor3;
    }

    public void setProfessor3(String professor3) {
        this.professor3 = professor3;
    }

    public String getFieldOfStudyAndSemester() {
        return fieldOfStudyAndSemester;
    }

    public void setFieldOfStudyAndSemester(String courseAndSemester) {
        this.fieldOfStudyAndSemester = courseAndSemester;
    }
}
