package com.group3.backend.controller;

import java.time.LocalDate;

public class CalendarEntryDateRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String matrNr;

    private CalendarEntryDateRequest(LocalDate startDate, LocalDate endDate, String matrNr){
        this.startDate = startDate;
        this.endDate = endDate;
        this.matrNr = matrNr;
    }

    private CalendarEntryDateRequest(){

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
}
