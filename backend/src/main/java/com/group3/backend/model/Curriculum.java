package com.group3.backend.model;

import java.util.List;
import lombok.Data;

@Data
public class Curriculum {

    private String id;
    private String description;
    private List<Milestone> milestone;
    private List<String> notes;

    /*public Curriculum() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    }*/
}