package com.group3.backend.model;

import com.group3.backend.StringListConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Curriculum {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String description;
    ///https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa
    @Convert(converter = StringListConverter.class)
    private Set<String>notesSet;
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Milestone> milestoneSet;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Curriculum() {
    }

    public Curriculum(String description, Set<String> notesSet, Set<Milestone> milestoneSet, Student student) {
        this.description = description;
        this.notesSet = notesSet;
        this.milestoneSet = milestoneSet;
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getNotesSet() {
        return notesSet;
    }

    public void setNotesSet(Set<String> notesSet) {
        this.notesSet = notesSet;
    }

    public Set<Milestone> getMilestoneList() {
        return milestoneSet;
    }

    public void setMilestoneList(Set<Milestone> milestoneSet) {
        this.milestoneSet = milestoneSet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
