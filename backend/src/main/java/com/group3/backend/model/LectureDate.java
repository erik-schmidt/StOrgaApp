package com.group3.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalTime;

@Entity
public class LectureDate {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Weekday weekday;
    private LocalTime startTime;
    private LocalTime finishTime;

    public LectureDate() {
    }

    public LectureDate(Weekday weekday, LocalTime startTime, LocalTime finishTime){
        this.weekday = weekday;
        this.startTime = startTime;
        this.finishTime = finishTime;
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

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }
}
