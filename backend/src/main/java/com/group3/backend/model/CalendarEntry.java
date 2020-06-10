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

    private String entryStartDateAndTime;

    private String entryFinishDateAndTime;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, String entryStartDateAndTime, String entryFinishDateAndTime, String description) {
        this.name = name;
        this.entryStartDateAndTime = entryStartDateAndTime;
        this.entryFinishDateAndTime = entryFinishDateAndTime;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntryStartDateAndTime() {
        return entryStartDateAndTime;
    }

    public void setEntryStartDateAndTime(String entryStartDateAndTime) {
        this.entryStartDateAndTime = entryStartDateAndTime;
    }

    public String getEntryFinishDateAndTime() {
        return entryFinishDateAndTime;
    }

    public void setEntryFinishDateAndTime(String entryFinishDateAndTime) {
        this.entryFinishDateAndTime = entryFinishDateAndTime;
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
