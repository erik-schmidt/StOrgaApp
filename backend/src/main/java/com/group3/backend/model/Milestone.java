package com.group3.backend.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Milestone {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    @Column(columnDefinition = "TIMESTAMP")
    @Type(type="org.hibernate.type.LocalDateTimeType")
    @Convert(disableConversion = true)
    private LocalDate finishDate;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curriculumid", nullable = false)
    private Curriculum curriculum;

    public Milestone() {
    }

    public Milestone(String text, LocalDate finishDate, Curriculum curriculum){
        this.text = text;
        this.finishDate = finishDate;
        this.curriculum = curriculum;
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

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }
}
