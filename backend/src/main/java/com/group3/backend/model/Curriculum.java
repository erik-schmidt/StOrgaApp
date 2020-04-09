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
    private Set<String>notesList;
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Milestone> milestoneList;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    public Curriculum() {
    }

    public Curriculum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public List<String> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<String> notesList) {
        this.notesList = notesList;
    }*/

    public Set<Milestone> getMilestoneList() {
        return milestoneList;
    }

    public void setMilestoneList(Set<Milestone> milestoneList) {
        this.milestoneList = milestoneList;
    }
}
