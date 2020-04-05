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

    // TODO: 01.04.2020 how to save primitive lists https://stackoverflow.com/questions/287201/how-to-persist-a-property-of-type-liststring-in-jpa
    @Convert(converter = StringListConverter.class)
    private Set<String>notesList;
    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Milestone> milestoneList;

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
