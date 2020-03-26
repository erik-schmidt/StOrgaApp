package com.group3.backend;

public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeeEMail;
    private String employeeAdress;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEMail;
    }

    public void setEmployeeEmail(String getEmployeeMail) {
        this.employeeEMail = getEmployeeMail;
    }

    public String getEmployeeAddress() {
        return employeeAdress;
    }

    public void setEmployeeAddress(String employeeAdress) {
        this.employeeAdress = employeeAdress;
    }
}
