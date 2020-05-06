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

    public StudentAccount() {
    }

    public StudentAccount(String username, String password, String matriculationNumber){
        this.username = username;
        this.password = password;
        this.matriculationNumber = matriculationNumber;
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

    public String getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(String matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }
}
