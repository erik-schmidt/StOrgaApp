package com.group3.backend.model;


import java.time.LocalTime;
import lombok.Data;

@Data
public class LectureDate {

    private String id;
    private WeekDay weekDay;
    private LocalTime startTime;
    private LocalTime finishTime;

    /*public LectureDate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
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
    }*/
}
