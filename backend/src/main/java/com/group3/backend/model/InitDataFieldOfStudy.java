package com.group3.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InitDataFieldOfStudy {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String name;
    @Min(1)
    @Max(15)
    private int semester;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "initDataFieldOfStudy")
    private Set<InitDataCourse> courseList = new HashSet<>();

    public InitDataFieldOfStudy(String name,String description, int semester, Set<InitDataCourse> courseList) {
        this.name = name;
        this.description = description;
        this.semester = semester;
        this.courseList = courseList;
    }

    public InitDataFieldOfStudy() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Set<InitDataCourse> getCourseList() {
        return courseList;
    }

    public void setCourseList(Set<InitDataCourse> courseList) {
        this.courseList = courseList;
    }
}
