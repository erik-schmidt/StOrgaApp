package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    public CourseController(CourseRepository courseRepository, StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return "reachable";}

    @GetMapping("/get")
    public List<Course> getAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    @GetMapping("/{matrNr}/get")
    public Set<Course> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr){
        Student student = studentRepository.findByMatrNr(matrNr);
        return student.getCourses();
    }

    @GetMapping("/get/{description}")
    public Course getCourseByNumber(@PathVariable(value = "description") String description){
        Course cs = courseRepository.findByDescription(description);
        return cs;
    }

    @PutMapping("/{matrNr}/addCourseToStudent")
    public ResponseEntity<Course> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course){
        try {
            Student student = studentRepository.findByMatrNr(matrNr);
            Course course1 = courseRepository.findByDescription(course.getDescription());
            Set<Student> studentSet = new HashSet<>();
            studentSet.add(student);
            course1.setStudents(studentSet);
            courseRepository.save(course1);
            courseRepository.saveAndFlush(course1);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course cs = new Course(course.getDescription(), course.getProfessor());
        courseRepository.save(cs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{description}")
    public Course deleteCourse(@PathVariable(value = "description") String description){
        Course course = courseRepository.findByDescription(description);
        courseRepository.delete(course);
        return course;
    }
}
