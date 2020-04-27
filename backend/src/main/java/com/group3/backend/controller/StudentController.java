package com.group3.backend.controller;

import com.group3.backend.model.Student;
import com.group3.backend.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Student
 * //assert != null
 * // Get => Daten holen
 * // POST => Daten erstellen/Eintragen
 * // PUT => Hinzufügen von relations
 * // PATCH => Updaten von einzelnen feldern
 */
@RestController
@RequestMapping("/student")
@CrossOrigin()
public class StudentController {

    private StudentService studentService;
    private static final Logger LOGGER=LoggerFactory.getLogger(StudentController.class);

    @Autowired
    public StudentController(StudentService studentService) {
         this.studentService = studentService;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return studentService.ping();
    }

    /**
     * getAllStudnets
     * return a List of all Students saved in the Database
     * @return List<Student>
     */
    @GetMapping("/get")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    /**
     * getStudentByNumber
     * return a Student with the given number
     * @param matNr
     * @return Student
     */
    @GetMapping("/get/{matNr}")
    public Student getStudentByNumber(@PathVariable(value = "matNr") String matNr){
        return studentService.getStudentByNumber(matNr);
    }

    /**
     * createStudent
     * create a student in the Database if it not exists already
     * @param student
     * @return ResponseEntity<String> if succesfull return id of student
     */
    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    /**
     * deleteStudent
     * delete a Student with the giben matriculation number out of the database
     * @param matNr
     * @return Student object
     */
    @DeleteMapping("/delete/{matNr}")
    public Student deleteStudent(@PathVariable(value = "matNr") String matNr){
        return studentService.deleteStudent(matNr);
    }
}
