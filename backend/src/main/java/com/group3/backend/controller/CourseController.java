package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    // TODO: 24.04.2020 search by course number not by description

    /**
     * ping()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return courseService.ping();
    }

    /**
     * getAllCourses
     * get all available courses in the database
     * @return List<Course> </Course>
     */
    @GetMapping("/get")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    /**
     * getCourseByNumger
     * get course information by search with the course number
     * @param number String
     * @return Course
     */
    @GetMapping("/get/{number}")
    public Course getCourseByNumber(@PathVariable(value = "number") String number){
        return courseService.getCourseByNumber(number);
    }

    /**
     * getStudentCourses
     * get all courses which the student is signed in
     * @param matrNr String
     * @return Set<Course>
     */
    @GetMapping("/get/{matrNr}")
    public Set<Course> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr){
        return courseService.getStudentsCourses(matrNr);
    }

    /**
     * getStudentCourses
     * get all courses which the student is signed in
     * @param matrNr String Matriculation number
     * @param number String Course number
     * @return Course
     */
    // TODO: 24.04.2020 get one course objcet from student by search with matrnr, coursenr


    /**
     * addCourseToStudent
     * add a course to a student
     * @param matrNr String matriculation number
     * @param course Course object
     * @return
     */
    @PutMapping("/addCourseToStudent/{matrNr}")
    public ResponseEntity<Course> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course){
        return courseService.addCourseToStudent(matrNr, course);
    }

    /**
     * createCourse
     * add a new course to the database
     * @param course Course
     * @return ResponseEntity<Course>
     */
    // TODO: 24.04.2020 not available for frontend
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    /**
     * deleteCourse
     * delete a course mapping of a student
     * @param number String matricuatlion number
     * @return Course
     */
    // TODO: 24.04.2020 add student matricualtion number for delete mapping
    @DeleteMapping("/delete/{number}")
    public Course deleteCourse(@PathVariable(value = "number") String number){
        return courseService.deleteCourse(number);
    }
}
