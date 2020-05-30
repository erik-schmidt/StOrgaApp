package com.group3.backend.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "TimeTableObject")
public class TimeTableObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(columnDefinition = "TIME")
    private LocalTime startTime;
    @Column(columnDefinition = "TIME")
    private LocalTime finishTime;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    private String courseNumber;
    private String summary;
    private String UID;
    private String location;
    private String description;
    private String fieldOfStudySemester;


    public TimeTableObject() {
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTimeDate) {
        this.startTime = startTimeDate;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTimeDate) {
        this.finishTime = finishTimeDate;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getFieldOfStudySemester() {
        return fieldOfStudySemester;
    }

    public void setFieldOfStudySemester(String courseAndSemester) {
        this.fieldOfStudySemester = courseAndSemester;
    }
}
