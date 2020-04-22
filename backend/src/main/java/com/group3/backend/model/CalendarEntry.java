package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity(name = "CalendarEntry")
public class CalendarEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String dateOfEntry;
    private String description;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, String dateOfEntry, String description){
        this.name = name;
        this.dateOfEntry = dateOfEntry;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(String dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
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

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalendarEntry )) return false;
        return id != null && id.equals((CalendarEntry) o).getId();
    }
     */
}
