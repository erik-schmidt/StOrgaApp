package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalTime;

/**
 * Model to represent an entry of the calendar.
 *
 * The name defines how the entry is called. The entryStartTime is the time from
 * where the entry begins. The entryFinishTime is the time when the entry ends.
 * The entryDate is simply the date of the entry in the calendar. The
 * description is a text to give the user extra information about the entry.
 */

@Entity(name = "CalendarEntry")
public class CalendarEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    @Column(columnDefinition = "TIME")
    private LocalTime entryStartTime;

    @Column(columnDefinition = "TIME")
    private LocalTime entryFinishTime;

    @Column(columnDefinition = "DATE")
    private LocalDate entryDate;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, LocalTime entryStartTime, LocalTime entryFinishTime, LocalDate entryDate,
            String description) {
        this.name = name;
        this.entryStartTime = entryStartTime;
        this.entryFinishTime = entryFinishTime;
        this.entryDate = entryDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getEntryStartTime() {
        return entryStartTime;
    }

    public void setEntryStartTime(LocalTime entryStartTime) {
        this.entryStartTime = entryStartTime;
    }

    public LocalTime getEntryFinishTime() {
        return entryFinishTime;
    }

    public void setEntryFinishTime(LocalTime entryFinishTime) {
        this.entryFinishTime = entryFinishTime;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
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
