package com.group3.backend.model;

import com.group3.backend.dataHandling.DataHandler;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
public class InitDataLectureDate {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Weekday weekday;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalTimeType")
    @Convert(disableConversion = true)
    private LocalTime startTime;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalTimeType")
    @Convert(disableConversion = true)
    private LocalTime finishTime;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private InitDataCourse course;

    public InitDataLectureDate() {
    }

    public InitDataLectureDate(Weekday weekday, LocalTime startTime, LocalTime finishTime, InitDataCourse course){
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

    public InitDataCourse getCourse() {
        return course;
    }

    public void setCourse(InitDataCourse course) {
        this.course = course;
    }

    public Weekday getWeekdayByName(String weekday){
        String weekdayCut = weekday.toLowerCase().trim();
        switch(weekdayCut){
            case "monday":
                return Weekday.MONDAY;
            case "tuesday":
                return Weekday.TUESDAY;
            case "wednesday":
                return Weekday.WEDNESDAY;
            case "thursday":
                return Weekday.THURSDAY;
            case "friday":
                return Weekday.FRIDAY;
            case "satruday":
                return Weekday.SATURDAY;
            case "sunday":
                return Weekday.SUNDAY;
        }
        return null;
    }

    private enum Weekday {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}