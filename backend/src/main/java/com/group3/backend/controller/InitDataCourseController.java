package com.group3.backend.controller;

import com.group3.backend.model.InitDataCourse;
import com.group3.backend.repository.InitDataCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/initDataCourse")
public class InitDataCourseController {

    private InitDataCourseRepository courseRepository;

    @Autowired
    public InitDataCourseController(InitDataCourseRepository courseRepository){
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
    public List<InitDataCourse> getAllCourses(){
        List<InitDataCourse> courseList = courseRepository.findAll();
        return courseList;
    }

    @GetMapping("/get/{id}")
    public InitDataCourse getCourseByNumber(@PathVariable(value = "id") int id){
        InitDataCourse cs = courseRepository.findById(id);
        return cs;
    }

    @PutMapping("/post")
    public ResponseEntity<InitDataCourse> createCourse(@RequestBody InitDataCourse course){
        InitDataCourse cs = new InitDataCourse(course.getDescription(), course.getRoom(), course.getProfessor(),
                course.getEcts(), course.getInitDataFieldOfStudy(), course.getLectureDateList());
        courseRepository.save(cs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public InitDataCourse deleteCourse(@PathVariable(value = "id") int id){
        InitDataCourse course = courseRepository.findById(id);
        courseRepository.delete(course);
        return course;
    }
}