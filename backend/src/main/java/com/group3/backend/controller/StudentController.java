package com.group3.backend.controller;


import com.group3.backend.model.Student;
import com.group3.backend.model.Task;
import com.group3.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

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
public class StudentController {

    private StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * reachabilityTest()
     * return a String with a successfull message if backend reachable
     * @return String "Test successfull"
     */
    @GetMapping("/ping")
    public String ping(){
        return "reachable";
    }

    @GetMapping("/get")
    public List<Student> getAllStudents(){

        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    @PostMapping("/post")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student st = new Student(student.getMatrNr(), student.getStudentPrename(), student.getStudentFamilyname(), student.getTaskLists());
        Set<Task> taskLists = st.getTaskLists();
        for(Task t: taskLists){
            t.setStudent(st);
        }
        st.setTaskLists(taskLists);
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
