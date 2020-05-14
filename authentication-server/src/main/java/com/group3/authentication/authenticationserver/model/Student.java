package com.group3.authentication.authenticationserver.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Size(min = 6)
    @Size(max = 6)
    private String matrNr;
    @Size(min = 2)
    @Size(max = 50)
    private String studentPrename;
    @Size(min = 2)
    @Size(max = 50)
    private String studentFamilyname;
    private String fieldOfStudy;
    @Min(1)
    @Max(15)
    private int currentSemester;
    private String username;
    private String password;

    public Student(String matrNr, String studentPrename, String studentFamilyname,
                   String fieldOfStudy, int currentSemester) {
        this.matrNr = matrNr;
        this.studentPrename = studentPrename;
        this.studentFamilyname = studentFamilyname;
        this.fieldOfStudy = fieldOfStudy;
        this.currentSemester = currentSemester;
    }

    public Student() {
    }

    public String getMatrNr() {
        return matrNr;
    }

    public void setMatrNr(String matrNr) {
        this.matrNr = matrNr;
    }

    public String getStudentPrename() {
        return studentPrename;
    }

    public void setStudentPrename(String studentPrename) {
        this.studentPrename = studentPrename;
    }

    public String getStudentFamilyname() {
        return studentFamilyname;
    }

    public void setStudentFamilyname(String studentFamilyname) {
        this.studentFamilyname = studentFamilyname;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}