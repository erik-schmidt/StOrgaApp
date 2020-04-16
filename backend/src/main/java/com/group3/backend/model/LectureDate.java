package com.group3.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class LectureDate {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Weekday weekday;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalDateTimeType")
    @Convert(disableConversion = true)
    private LocalTime startTime;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalDateTimeType")
    @Convert(disableConversion = true)
    private LocalTime finishTime;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public LectureDate() {
    }

    public LectureDate(Weekday weekday, LocalTime startTime, LocalTime finishTime, Course course){
        this.weekday = weekday;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.course = course;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime){
        this.finishTime = finishTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
