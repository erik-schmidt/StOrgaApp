package com.group3.backend.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Milestone {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    private LocalDate finishDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curriculumid", nullable = false)
    private Curriculum curriculum;

    public Milestone() {
    }

    public Milestone(String text, LocalDate finishDate){
        this.text = text;
        this.finishDate = finishDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
