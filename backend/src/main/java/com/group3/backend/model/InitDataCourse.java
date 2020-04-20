package com.group3.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
// TODO: 15.04.2020 SChwerpunkte eintragen?  
public class InitDataCourse {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String room;
    private String professor;
    @Min(1)
    @Max(50)
    private int ects;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // TODO: 01.04.2020 can save list or need Set
    private Set<InitDataLectureDate> lectureDateList;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "initDataFieldOfStudy_id", nullable = false)
    private InitDataFieldOfStudy initDataFieldOfStudy;

    public InitDataCourse(String description, String room, String professor, int ects, InitDataFieldOfStudy initDataFieldOfStudy, Set<InitDataLectureDate> lectureDateList) {
        this.description = description;
        this.room = room;
        this.professor = professor;
        this.ects = ects;
        this.initDataFieldOfStudy = initDataFieldOfStudy;
        this.lectureDateList = lectureDateList;
    }

    public InitDataCourse() {
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

    public InitDataFieldOfStudy getInitDataFieldOfStudy() {
        return initDataFieldOfStudy;
    }

    public void setInitDataFieldOfStudy(InitDataFieldOfStudy initDataFieldOfStudy) {
        this.initDataFieldOfStudy = initDataFieldOfStudy;
    }

    public Set<InitDataLectureDate> getLectureDateList() {
        return lectureDateList;
    }

    public void setLectureDateList(Set<InitDataLectureDate> lectureDateList) {
        this.lectureDateList = lectureDateList;
    }
}
