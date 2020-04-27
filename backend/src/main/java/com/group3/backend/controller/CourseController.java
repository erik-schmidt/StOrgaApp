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

    /**
     * Test method to try if the connection is established.
     * @return
     */
    @GetMapping("/ping")
    public String ping(){
        return courseService.ping();}


    /**
     * Returns a list of all courses.
     * @return
     */
    @GetMapping("/get")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    /**
     * Returns a set of all courses of the student.
     * @param matrNr
     * @return
     */
    @GetMapping("/{matrNr}/get")
    public Set<Course> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr){
        return courseService.getStudentsCourses(matrNr);
    }

    /**
     * Returns specific course by its number.
     * @param number
     * @return
     */
    @GetMapping("/get/{number}")
    public Course getCourseByNumber(@PathVariable(value = "number") String number){
        return courseService.getCourseByNumber(number);
    }

    /**
     * Returns the grade of a specific course of a specific student.
     * @param matrNr
     * @param number
     * @return
     */
    @GetMapping("/get/{matrNr}/{number}")
    public double getGradeByMatrNrAndCourseNumber(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        return courseService.getGradeByMatrNrAndCourseNumber(matrNr, number);
    }


    /**
     * Adds a course to a student.
     * @param matrNr
     * @param course
     * @return
     */
    @PutMapping("/add/{matrNr}/")
    public ResponseEntity<Course> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course){
        return courseService.addCourseToStudent(matrNr, course);
    }

    /**
     * Set the grade of specific course of a specific student.
     * @param matrNr
     * @param number
     * @param grade
     * @return
     */
    @PutMapping("/grade/{matrNr}/{number}/{grade}")
    public double setGradeOfCourse(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @PathVariable(value = "garde") double grade){
        return courseService.getGradeByMatrNrAndCourseNumber(matrNr, number);
    }


    /**
     * Method to create a course.
     * @param course
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }


    /**
     * Method to delete a specific course.
     * @param number
     * @return
     */
    @DeleteMapping("/delete/{number}")
    public Course deleteCourse(@PathVariable(value = "number") String number){
        return courseService.deleteCourse(number);
    }

    /**
     * Method to delet a specific course of a specific student.
     * @param matrNr
     * @param number
     * @return
     */
    @DeleteMapping("/delete/{matrNr}/{number}")
    public Course deleteCourseFromStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        return courseService.deleteCourseFromStudent(matrNr, number);
    }
}
