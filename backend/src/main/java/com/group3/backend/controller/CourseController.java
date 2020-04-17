package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
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

    @GetMapping("/get/{id}")
    public Course getCourseByNumber(@PathVariable(value = "id") int id){
        Course cs = courseRepository.findById(id);
        return cs;
    }

    @PutMapping("/post")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course cs = new Course(course.getDescription(), course.getGrade(), course.getStudent());
        courseRepository.save(cs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Course deleteCourse(@PathVariable(value = "id") int id){
        Course course = courseRepository.findById(id);
        courseRepository.delete(course);
        return course;
    }
}
