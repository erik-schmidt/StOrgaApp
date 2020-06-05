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


@Entity(name = "CalendarEntry")
public class CalendarEntry implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String name;

    /*
    @Column(columnDefinition = "TIME")
   // @Convert(disableConversion = true)
    private LocalTime entryStartTime;

    @Column(columnDefinition = "TIME")
    //@Convert(disableConversion = true)
    private LocalTime entryFinishTime;

    @Column(columnDefinition = "DATE")
    //@Convert(disableConversion = true)
    private LocalDate entryDate;
     */

    @Column(name = "startTime", columnDefinition = "TIMESTAMP")
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp entryStartTime;

    @Column(name = "finishTime", columnDefinition = "TIMESTAMP")
    //@Temporal(TemporalType.TIMESTAMP)
    private Timestamp entryFinishTime;

    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student")
    private Student student = new Student();

    public CalendarEntry() {

    }

    public CalendarEntry(String name, Timestamp entryStartTime, Timestamp entryFinishTime, String description){
        this.name = name;
        this.entryStartTime = entryStartTime;
        this.entryFinishTime = entryFinishTime;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getEntryStartTime() {
        return entryStartTime;
    }

    public void setEntryStartTime(Timestamp entryStartTime) {
        this.entryStartTime = entryStartTime;
    }

    public Timestamp getEntryFinishTime() {
        return entryFinishTime;
    }

    public void setEntryFinishTime(Timestamp entryFinishTime) {
        this.entryFinishTime = entryFinishTime;
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
