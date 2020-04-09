package com.group3.backend.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
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
    //private Set<Integer> courseIdList;
    //@OneToOne(mappedBy = "fieldOfStudy", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    //private Student student;

    public FieldOfStudy() {
    }

    public FieldOfStudy(String description, String name, int semester) {
        this.description = description;
        this.name = name;
        this.semester = semester;
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


}
