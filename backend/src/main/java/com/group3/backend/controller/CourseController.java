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


    @GetMapping("/ping")
    public String ping(){
        return courseService.ping();}

    @GetMapping("/get")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("/{matrNr}/get")
    public Set<Course> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr){
        return courseService.getStudentsCourses(matrNr);
    }

    @GetMapping("/get/{description}")
    public Course getCourseByNumber(@PathVariable(value = "description") String description){
        return courseService.getCourseByNumber(description);
    }

    @PutMapping("/{matrNr}/addCourseToStudent")
    public ResponseEntity<Course> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course){
        return courseService.addCourseToStudent(matrNr, course);
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @DeleteMapping("/delete/{description}")
    public Course deleteCourse(@PathVariable(value = "description") String description){
        return courseService.deleteCourse(description);
    }
}
