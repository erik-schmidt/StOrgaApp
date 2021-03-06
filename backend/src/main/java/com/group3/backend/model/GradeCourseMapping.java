package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Model to save the grade of a {@link Course} from a {@link Student}.
 *
 * The courseNumber is the key to get the specific {@link Course}.
 * The grade is a double between 1-5 which is the grade of the {@link Student}.
 */

@Entity
public class GradeCourseMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String courseNumber;
    @Min(0)
    @Max(6)
    private double grade;
    @NotNull
    private String courseName;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    public GradeCourseMapping() {
    }

    public GradeCourseMapping(String courseNumber, double grade, String courseName) {
        this.courseNumber = courseNumber;
        this.grade = grade;
        this.courseName = courseName;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(courseNumber + " ");
        sb.append(grade + " ");
        sb.append(courseName);
        return sb.toString();
    }
}
