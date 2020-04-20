package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Student implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 6)
    @Size(max = 6)
    private String matrNr;
    @Size(min = 2)
    @Size(max = 50)
    private String studentPrename;
    @Size(min = 2)
    @Size(max = 50)
    private String studentFamilyname;
    private String fieldOfStudy;
    @Min(1)
    @Max(15)
    private int currentSemester;
    //@Nullable
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    //@ManyToMany(cascade = CascadeType.ALL)
    /*@JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))*/
    //private List<Course> courseList;

    //@OneToMany(fetch = FetchType.LAZY,  orphanRemoval = true)
    //private List<Course> courseList = new ArrayList<Course>();

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    @Nullable
    private Set<Curriculum> calenderEntries = new HashSet<>();*/

    public Student(String matrNr, String studentPrename, String studentFamilyname,
                   Set<Curriculum> calenderEntries, String fieldOfStudy, int currentSemester) {
        this.matrNr = matrNr;
        this.studentPrename = studentPrename;
        this.studentFamilyname = studentFamilyname;
        //this.courseList = courseList;
        //this.calenderEntries = calenderEntries;
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

    /*public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }*/

    /*public Set<Curriculum> getCalenderEntries() {
        return calenderEntries;
    }

    public void setCalenderEntries(Set<Curriculum> calenderEntries) {
        this.calenderEntries = calenderEntries;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(matrNr + " ");
        sb.append(studentPrename + " ");
        sb.append(studentFamilyname + " ");
        sb.append(fieldOfStudy + " ");
        sb.append("Semester: " + currentSemester);
        return sb.toString();
    }
}