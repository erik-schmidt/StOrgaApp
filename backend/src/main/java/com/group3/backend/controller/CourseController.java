package com.group3.backend.controller;

import com.group3.backend.model.Course;
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

    @GetMapping("/get")
    public List<Course> getAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    @PutMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course cs = new Course(course.getDescription(), course.getRoom(), course.getProfessor(), course.getEcts(), course.getGrade(), course.getFieldOfStudy());
        courseRepository.save(cs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Course deleteCourse(@PathVariable(value = "id") int id){
        Course course = courseRepository.findById(id);
        courseRepository.delete(course);
        return course;
    }
}
