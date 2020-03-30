package com.group3.backend.model;

import java.util.List;

import lombok.Data;

@Data
public class Student {
    private String matrNr;
    private String studentPrename;
    private String studentFamilyname;
    private FieldOfStudy fieldOfStudy;
    private List<Course> courseList;
    private List<Task> taskList;
    private List<String> calenderEntries;

    /*public Student() {
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

    public FieldOfStudy getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
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

    public ArrayList<String> getCalenderEntries() {
        return calenderEntries;
    }

    public void setCalenderEntries(ArrayList<String> calenderEntries) {
        this.calenderEntries = calenderEntries;
    }*/
}
