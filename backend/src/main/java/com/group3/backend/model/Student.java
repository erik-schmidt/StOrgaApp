package com.group3.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
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
    private String fieldOfStudy;
    @Min(1)
    @Max(15)
    private int currentSemester;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Task> taskLists = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Course> courseList = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private Set<Curriculum> calenderEntries = new HashSet<>();
    //@OneToOne(fetch = FetchType.LAZY)
    //private FieldOfStudy fieldOfStudy;

    public Student(String matrNr, String studentPrename, String studentFamilyname, Set<Task> taskLists,
                   Set<Course> courseList, Set<Curriculum> curriculumSet, String fieldOfStudy, int currentSemester) {
        this.matrNr = matrNr;
        this.studentPrename = studentPrename;
        this.studentFamilyname = studentFamilyname;
        this.taskLists = taskLists;
        this.courseList = courseList;
        this.calenderEntries = calenderEntries;
        this.fieldOfStudy = fieldOfStudy;
        this.currentSemester = currentSemester;
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

    public Set<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<Course> courseList) {
        this.courseList = courseList;
    }

    public Set<Curriculum> getCalenderEntries() {
        return calenderEntries;
    }

    public void setCalenderEntries(Set<Curriculum> calenderEntries) {
        this.calenderEntries = calenderEntries;
    }

    /*public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }*/

    /*public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }*/

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }
}