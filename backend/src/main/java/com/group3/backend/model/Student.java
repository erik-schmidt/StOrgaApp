package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
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
    // TODO: 01.06.2020 Was wenn man im Semester 0 ist weil man noch nicht eingeschrieben ist? @Chris
    private int currentSemester = 1;
    private String username;
    private String password;
    @JsonIgnore
    @ManyToMany(mappedBy = "students", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Course> courses = new HashSet<>();
    /*
     * @JsonIgnore
     * 
     * @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true )
     */
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<GradeCourseMapping> gradeCourseMappings = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<CalendarEntry> calendarEntries = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Link> links = new HashSet<>();

    public Student(String matrNr, String studentPrename, String studentFamilyname, String fieldOfStudy,
            int currentSemester) {
        this.matrNr = matrNr;
        this.studentPrename = studentPrename;
        this.studentFamilyname = studentFamilyname;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<GradeCourseMapping> getGradeCourseMappings() {
        return gradeCourseMappings;
    }

    public void setGradeCourseMappings(Set<GradeCourseMapping> gradeCourseMappings) {
        this.gradeCourseMappings = gradeCourseMappings;
    }


    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CalendarEntry> getCalendarEntries() {
        return calendarEntries;
    }

    public void setCalendarEntries(Set<CalendarEntry> calendarEntries) {
        this.calendarEntries = calendarEntries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(matrNr + " ");
        sb.append(studentPrename + " ");
        sb.append(studentFamilyname + " ");
        sb.append(fieldOfStudy + " ");
        sb.append("Semester: " + currentSemester);
        return sb.toString();
    }
}