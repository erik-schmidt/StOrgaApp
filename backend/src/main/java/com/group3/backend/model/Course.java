package com.group3.backend.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Course {

    private String description;
    private String room;
    private String professor;
    private int ects;
    private ArrayList<LectureDate> lectureDateArrayList;
    private LocalDateTime[] courseAppointmentsPerWeek;

    public ArrayList<LectureDate> getLectureDateArrayList() {
        return lectureDateArrayList;
    }

    public void setLectureDateArrayList(ArrayList<LectureDate> lectureDateArrayList) {
        this.lectureDateArrayList = lectureDateArrayList;
    }

    public Course() {
        LocalDateTime[]localDateTimes = new LocalDateTime[5];
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
}