package com.group3.backend.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Task() {
    }


    public Task(String description, Student student){
        this.description = description;
        this.student = student;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
