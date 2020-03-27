package com.group3.backend.service.studentService;


import com.group3.backend.model.Student;

import java.util.List;

public interface StudentDao {

    List<Student> findAll();

    void insterStudent(Student student);

    void updateStudent(Student student);

    void executeUpdateStudent(Student student);

    public void deleteStudent(Student student);

}