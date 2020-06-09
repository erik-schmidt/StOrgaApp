package com.group3.backend.controller;

import com.group3.backend.model.Student;
import com.group3.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/student")
@CrossOrigin()
public class StudentController {

    private StudentService studentService;
    private AccessChecker accessChecker;

    @Autowired
    public StudentController(StudentService studentService, AccessChecker accessChecker) {
        this.studentService = studentService;
        this.accessChecker = accessChecker;
    }

    /**
     * return a String with a successful message if backend reachable
     * @return String "reachable"
     */
    @GetMapping("/ping")
    public String ping(){
        return studentService.ping();
    }

    /**
     * The get-method to get all {@link Student} objects from the repository.
     * @param token
     *              The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          requested {@link Student} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/get")
    public ResponseEntity<?> getAllStudents(@RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return studentService.getAllStudents();
    }

    /**
     * The get-method to get a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to get.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          requested {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matNr}/get")
    public ResponseEntity<?> getStudentByNumber(@PathVariable(value = "matNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
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
     * The delete-method to delete a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          deleted {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @DeleteMapping("/{matrNr}/delete")
    public ResponseEntity<?> deleteStudent(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return studentService.deleteStudent(matrNr);
    }

    /**
     * The update-method to update a existing {@link Student}.
     * @param student
     *                  The {@link Student} you want the actual one to be replaced with.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          new {@link Student} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @PutMapping("/update")
    public ResponseEntity<?> updateStudent(@RequestBody Student student, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(student.getMatrNr(), token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return studentService.updateStudent(student);
    }


}
