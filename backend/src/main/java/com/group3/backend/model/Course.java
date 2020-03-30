package com.group3.backend.model;

import java.util.List;
import lombok.Data;

@Data
public class Course {

    private String id;
    private String description;
    private String room;
    private String professor;
    private Integer ects;
    private Double grade;
    private String fieldOfStudy;
    private List<LectureDate> lectureDateArrayList;

    /*public ArrayList<LectureDate> getLectureDateArrayList() {
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
    }*/
}