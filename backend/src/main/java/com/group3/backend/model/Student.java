package com.group3.backend.model;


import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 6)
    private String matrNr;
    private String studentPrename;
    private String studentFamilyname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Task> taskLists = new HashSet<>();
   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Course> courseList = new HashSet<>();;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Curriculum> calenderEntries = new HashSet<>();*/
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "fieldOfStudy_id")
    //private FieldOfStudy fieldOfStudy;;

    public Student(String matrNr, String studentPrename, String studentFamilyname, Set<Task> taskLists) {
        this.matrNr = matrNr;
        this.studentPrename = studentPrename;
        this.studentFamilyname = studentFamilyname;
        this.taskLists = taskLists;
        //this.courseList = courseList;
       // this.calenderEntries = calenderEntries;
        //this.fieldOfStudy = new FieldOfStudy();
       // this.fieldOfStudy = fieldOfStudy;
    }

    public Student() {
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }

    public String getStudentPrename() {
        return studentPrename;
    }

    public void setStudentPrename(String studentPrename) {
        this.studentPrename = studentPrename;
    }

    public String getStudentFamilyname() {
        return studentFamilyname;
    }

    public void setStudentFamilyname(String studentFamilyname) {
        this.studentFamilyname = studentFamilyname;
    }

    public Set<Task> getTaskLists() {
        return taskLists;
    }

    public void setTaskLists(Set<Task> taskLists) {
        this.taskLists = taskLists;
    }
}