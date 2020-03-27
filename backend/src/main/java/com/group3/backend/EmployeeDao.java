package com.group3.backend;


import java.util.List;

public interface EmployeeDao {

    List<Student> findAll();

    void insertEmployee(Student emp);

    void updateEmployee(Student emp);

    void executeUpdateEmployee(Student emp);

    public void deleteEmployee(Student emp);

}