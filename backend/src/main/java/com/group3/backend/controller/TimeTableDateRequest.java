package com.group3.backend.controller;

import java.time.LocalDate;

public class TimeTableDateRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String matrNr;
    private boolean currentWeek;
    private Integer timePeriod;
    private boolean onlyJoinedCourses;

    public TimeTableDateRequest(LocalDate startDate, LocalDate endDate, String matrNr, boolean currentWeek, Integer timePeriod, boolean onlyJoinedCourses) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.matrNr = matrNr;
        this.currentWeek = currentWeek;
        this.timePeriod = timePeriod;
        this.onlyJoinedCourses = onlyJoinedCourses;
    }

    public TimeTableDateRequest() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }

    public boolean isCurrentWeek() {
        return currentWeek;
    }

    public void setCurrentWeek(boolean currentWeek) {
        this.currentWeek = currentWeek;
    }

    public Integer getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(Integer timePeriod) {
        this.timePeriod = timePeriod;
    }

    public boolean isOnlyJoinedCourses() {
        return onlyJoinedCourses;
    }

    public void setOnlyJoinedCourses(boolean onlyJoinedCourses) {
        this.onlyJoinedCourses = onlyJoinedCourses;
    }
}
