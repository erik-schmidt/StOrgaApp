package com.group3.backend.model;

import com.group3.backend.StringListConverter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

@Entity
public class FieldOfStudy {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    private String name;
    @Min(1)
    @Max(15)
    private int semester;
    //@OneToMany(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Convert(converter = StringListConverter.class)
    private Set<Integer> courseIdSet;
   /* @OneToOne(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;*/

    public FieldOfStudy() {
    }

    public FieldOfStudy(String description, String name, int semester, Set<Integer> courseIdSet) {
        this.description = description;
        this.name = name;
        this.semester = semester;
        this.courseIdSet = courseIdSet;
        //this.student = student;
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

    public Set<Integer> getCourseIdSet() {
        return courseIdSet;
    }

    public void setCourseIdSet(Set<Integer> courseIdSet) {
        this.courseIdSet = courseIdSet;
    }

    /*public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }*/
}
