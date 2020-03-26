package com.group3.backend;


import java.util.List;

import com.group3.backend.Employee;

public interface EmployeeDao {

    List<Employee> findAll();

    void insertEmployee(Employee emp);

    void updateEmployee(Employee emp);

    void executeUpdateEmployee(Employee emp);

    public void deleteEmployee(Employee emp);

}