package com.group3.backend.controller;


import com.group3.backend.model.Student;
import com.group3.backend.model.Task;
import com.group3.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Student
 *
 */

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/get")
    public List<Student> getAllStudents(){
        //assert != null
        // Get => Daten holen
        // POST => Daten erstellen/Eintragen
        // PUT => Hinzufügen von relations
        // PATCH => Updaten von einzelnen feldern
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        List<Task> taskLists = new ArrayList<>();
        Task task1 = new Task("Test Desc");
        taskLists.add(task1);

        Student st = new Student(student.getMatrNr(), student.getStudentPrename(), student.getStudentFamilyname());
        studentRepository.save(st);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{matNr}")
    public Student deleteStudent(@PathVariable(value = "matNr") String matNr){
        Student st = studentRepository.findByMatrNr(matNr);
        studentRepository.delete(st);
        return st;
    }

}
