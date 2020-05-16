package com.group3.backend.controller;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.service.GradeCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gradeCourseMapping")
@CrossOrigin()
public class GradeCourseMappingController {

    private GradeCourseMappingService gradeCourseMappingService;

    @Autowired
    public GradeCourseMappingController(GradeCourseMappingService gradeCourseMappingService) {
        this.gradeCourseMappingService = gradeCourseMappingService;
    }

    /**
     * addGradeToStudent
     * add a grade of a course to student
     * @param matrNr String
     * @param gradeCourseMapping GradeCourseMapping
     * @return ResoponesEntity
     */
    @PutMapping("/add/{matrNr}")
    public ResponseEntity<?> addGradeCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody GradeCourseMapping gradeCourseMapping){
        ResponseEntity<?> responseEntity = gradeCourseMappingService.addGradeCourseToStudent(matrNr, gradeCourseMapping);
        return responseEntity;
    }

    /**
     * addGradeCourseToStudent
     * get a grade course object of a studetn
     * @param matrNr String
     * @return ResoponesEntity
     */
    @GetMapping("/getAll/{matrNr}")
    public ResponseEntity<?> getAllGradeCourseToStudent(@PathVariable(value = "matrNr") String matrNr){
        ResponseEntity<?> responseEntity = gradeCourseMappingService.getAllGradeCourseOfStudent(matrNr);
        return responseEntity;
    }

    /**
     * getGradeCourseOFStudent
     * get the grade course mapping of a stundent and a specific couse
     * @param matrNr String matriculation number o Student
     * @param number String number of the curse
     * @return  ResponseEntity<?>
     */
    @GetMapping("/get/{matrNr}/{number}")
    public ResponseEntity<?> getGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        ResponseEntity<?> responseEntity = gradeCourseMappingService.getGradeCourseOfStudent(matrNr, number);
        return responseEntity;
    }

    /**
     * deleteGradeCourseOfStudent
     * delete Grade Course Mapping of a student
     * @param matrNr String matriculation number of student
     * @param number grade course mapping json
     * @return ResoponesEntity
     */
    @DeleteMapping("/delete/{matrNr}/{number}")
    public ResponseEntity<?> deleteGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number){
        ResponseEntity<?> responseEntity = gradeCourseMappingService.deleteGradeCourseOfStudent(matrNr, number);
        return responseEntity;
    }
}
