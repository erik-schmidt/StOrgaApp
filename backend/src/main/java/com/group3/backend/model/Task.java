package com.group3.backend.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Task {

    private String id;
    private String description;
    private LocalDate deadline;

    /*public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }*/
}