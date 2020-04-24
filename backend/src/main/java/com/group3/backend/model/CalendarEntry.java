package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity(name = "CalendarEntry")
public class CalendarEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    // TODO: 24.04.2020 Eventuell stimmen die Datentypen für die folgenden 3 Attribute nicht.
    @Column(columnDefinition = "TIME")
    @Type(type="org.hibernate.type.TIME")
    @Convert(disableConversion = true)
    private Time entryStartTime;

    @Column(columnDefinition = "TIME")
    @Type(type="org.hibernate.type.TIME")
    @Convert(disableConversion = true)
    private Time entryFinishTime;

    @Column(columnDefinition = "DATE")
    @Type(type="org.hibernate.type.DATE")
    @Convert(disableConversion = true)
    private Date entryDate;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, Time entryStartTimeString, Time entryFinishTime, Date entryDate, String description){
        this.name = name;
        this.entryStartTime = entryStartTimeString;
        this.entryFinishTime = entryFinishTime;
        this.entryDate = entryDate;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getEntryStartTime() {
        return entryStartTime;
    }

    public void setEntryStartTime(Time entryStartTime) {
        this.entryStartTime = entryStartTime;
    }

    public Time getEntryFinishTime() {
        return entryFinishTime;
    }

    public void setEntryFinishTime(Time entryFinishTime) {
        this.entryFinishTime = entryFinishTime;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
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
