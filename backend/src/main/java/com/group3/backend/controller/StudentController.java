package com.group3.backend.controller;

import com.group3.backend.model.Student;
import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtAuthenticatedProfile;
import com.group3.backend.security.JwtTokenService;
import com.group3.backend.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.Header;
import org.springframework.web.bind.annotation.*;

/**
 * StudentController
 * implemetns the Rest Api
 * //assert != null
 * // Get => Daten holen
 * // POST => Daten erstellen/Eintragen
 * // PUT => Hinzufügen von relations
 * // PATCH => Updaten von einzelnen feldern
 */
@RestController
@RequestMapping("interface/student")
@CrossOrigin()
public class StudentController {

    private StudentService studentService;
    private JwtTokenService jwtTokenService;
    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    private StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService, JwtTokenService jwtTokenService, StudentRepository studentRepository) {
         this.studentService = studentService;
         this.jwtTokenService = jwtTokenService;
         this.studentRepository  = studentRepository;
    }

    /**
     * ping()
     * return a String with a successful message if backend reachable
     * @return String "reachable"
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
    public ResponseEntity<?> getAllStudents(){
        return studentService.getAllStudents();
    }

    /**
     * getStudentByNumber
     * return a Student with the given number
     * @param matrNr
     * @return Student
     */
    @GetMapping("/get/{matNr}")
    public ResponseEntity<?> getStudentByNumber(@PathVariable(value = "matNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(!checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return studentService.getStudentByNumber(matrNr);
    }

    /**
     * createStudent
     * create a student in the Database if it not exists already
     * @param student
     * @return ResponseEntity<String> if succesfull return id of student
     */
    /*@PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }*/

    /**
     * deleteStudent
     * delete a Student with the giben matriculation number out of the database
     * @param matrNr
     * @return Student object
     */
    @DeleteMapping("/delete/{matrNr}")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(!checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return studentService.deleteStudent(matrNr);
    }

    /**
     * updateStudent
     * update a student which is already saved in the database
     * all attributes can be changed
     * REturn a response entity at success of fault
     * @param student object with the parameters for update
     * @return ResoponesEntity return String if fault, return sutdent object if successfull
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @RequestHeader (name="Authorization") String token){
        if(!checkAccess(student.getMatrNr(), token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return studentService.updateStudent(student);
    }

    private boolean checkAccess(String matrNr, String token){
        String extractToken = token.substring(7, token.length());
        String username =jwtTokenService.getUsernameFromToken(extractToken);
        String usernameRepo = studentRepository.findByMatrNr(matrNr).getUsername();
        if(!username.equals(usernameRepo)){
            return false;
        }
        return true;
    }


}
