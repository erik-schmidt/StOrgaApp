package com.group3.backend;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String matrNr;
    private String studentPrename;
    private String studentFamilyname;
    private DegreeCourse degreeCourse;
    private ArrayList<Course> courseList;
    private ArrayList<Task> taskList;
    private Grade grade;
    private ArrayList<String> calenderEntries;

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

    public DegreeCourse getDegreeCourse() {
        return degreeCourse;
    }

    public void setDegreeCourse(DegreeCourse degreeCourse) {
        this.degreeCourse = degreeCourse;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public ArrayList<String> getCalenderEntries() {
        return calenderEntries;
    }

    public void setCalenderEntries(ArrayList<String> calenderEntries) {
        this.calenderEntries = calenderEntries;
    }
}
