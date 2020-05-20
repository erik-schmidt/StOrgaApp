package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.service.CourseService;
import com.group3.backend.service.GradeCourseMappingService;
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
    private GradeCourseMappingService gradeCourseMappingService;

    @Autowired
    public CourseController(CourseService courseService, GradeCourseMappingService gradeCourseMappingService){
        this.courseService = courseService;
        this.gradeCourseMappingService = gradeCourseMappingService;
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
    public ResponseEntity<?> getAllCourses(){
        return courseService.getAllCourses();
    }

    /**
     * getCourseByNumber
     * get course information by search with the course number
     * @param number String
     * @return Course
     */
    @GetMapping("/get/{number}")
    public ResponseEntity<?> getCourseByNumber(@PathVariable(value = "number") String number){
        return courseService.getCourseByNumber(number);
    }

    /**
     * Get the courses by their kindOfSubject.
     * @param kindOfSubject
     * @return
     */
    @GetMapping("/get/{kindOfSubject}")
    public ResponseEntity<?> getCoursesByKindOfSubject(@PathVariable(value = "kindOfSubject") String kindOfSubject){
        return courseService.getCourseByKindOfSubject(kindOfSubject);
    }

    /**
     * Get the courses by their studyFocus.
     * @param studyFocus
     * @return
     */
    @GetMapping("/get/{studyFocus}")
    public ResponseEntity<?> getCoursesByStudyFocus(@PathVariable(value = "studyFocus") String studyFocus){
        return courseService.getCourseByStudyFocus(studyFocus);
    }

    /**
     * getStudentCourses
     * get all courses which the student is signed in
     * @param matrNr String
     * @return Set<Course>
     */
    @GetMapping("/get/{matrNr}")
    public ResponseEntity<?> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr){
        return courseService.getStudentsCourses(matrNr);
    }

    /**
     * getStudentCourses
     * get all courses which the student is signed in
     * @param matrNr String Matriculation number
     * @param number String Course number
     * @return Course
     */
   /* @GetMapping("/get/{matrNr}/{number}")
    public ResponseEntity<?> getGradeByMatrNrAndCourseNumber(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        return courseService.getGradeByMatrNrAndCourseNumber(matrNr, number);
    }*/

    /**
     * Set the grade of specific course of a specific student.
     * @param matrNr
     * @param number
     * @param grade
     * @return
     */
    /*@PutMapping("/grade/{matrNr}/{number}/{grade}")
    public ResponseEntity<?> setGradeOfCourse(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @PathVariable(value = "garde") double grade){
        return courseService.getGradeByMatrNrAndCourseNumber(matrNr, number);
    }*/

    /**
     * addCourseToStudent
     * add a course to a student
     * @param matrNr String matriculation number
     * @param course Course object
     * @return
     */
    @PutMapping("/add/{matrNr}")
    public ResponseEntity<?> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course){
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
    public ResponseEntity<?> createCourse(@RequestBody Course course){
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
    public ResponseEntity<?> deleteCourse(@PathVariable(value = "number") String number){
        return courseService.deleteCourse(number);
    }

    /**
     * Method to delet a specific course of a specific student.
     * @param matrNr
     * @param number
     * @return
     */
    @DeleteMapping("/delete/{matrNr}/{number}")
    public ResponseEntity<?> deleteCourseFromStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        List<Course> courseList = (List<Course>) courseService.getStudentsCourses(matrNr);
        Course course = new Course();
        for (Course c:courseList){
            if (c.getNumber().equals(number)){
                course = c;
            }
        }
        return courseService.deleteCourseFromStudent(matrNr, number);
    }
}
