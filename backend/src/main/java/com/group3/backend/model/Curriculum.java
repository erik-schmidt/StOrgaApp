package com.group3.backend.model;

import java.time.LocalDate;

public class Curriculum {

    private String id;
    private String description;
    private String notes;
    private LocalDate milestone;

    public Curriculum() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getMilestone() {
        return milestone;
    }

    public void setMilestone(LocalDate milestone) {
        this.milestone = milestone;
    }
}