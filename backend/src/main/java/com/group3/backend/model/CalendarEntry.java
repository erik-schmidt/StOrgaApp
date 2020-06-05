package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalTime;


@Entity(name = "CalendarEntry")
public class CalendarEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    // TODO: 24.04.2020 Eventuell stimmen die Datentypen für die folgenden 3 Attribute nicht.
    @Column(columnDefinition = "TIME")
   // @Convert(disableConversion = true)
    private LocalTime entryStartTime;

    @Column(columnDefinition = "TIME")
    //@Convert(disableConversion = true)
    private LocalTime entryFinishTime;

    @Column(columnDefinition = "DATE")
    //@Convert(disableConversion = true)
    private LocalDate entryDate;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, LocalTime entryStartTimeString, LocalTime entryFinishTime, LocalDate entryDate, String description){
        this.name = name;
        this.entryStartTime = entryStartTimeString;
        this.entryFinishTime = entryFinishTime;
        this.entryDate = entryDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
