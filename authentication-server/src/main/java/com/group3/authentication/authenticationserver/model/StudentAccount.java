package com.group3.authentication.authenticationserver.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class StudentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String matriculationNumber;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;


    public StudentAccount() {
    }

    public StudentAccount(String username, String password, String matriculationNumber, String firstname, String lastname){
        this.username = username;
        this.password = password;
        this.matriculationNumber = matriculationNumber;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }
}
