package com.group3.backend.controller;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.service.GradeCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/grade")
@CrossOrigin()
public class GradeCourseMappingController {

    private GradeCourseMappingService gradeCourseMappingService;
    private AccessChecker accessChecker;

    @Autowired
    public GradeCourseMappingController(GradeCourseMappingService gradeCourseMappingService, AccessChecker accessChecker) {
        this.gradeCourseMappingService = gradeCourseMappingService;
        this.accessChecker = accessChecker;
    }

    /**
     * addGradeToStudent
     * add a grade of a course to student
     * @param matrNr String
     * @param gradeCourseMapping GradeCourseMapping
     * @return ResoponesEntity
     */
    @PutMapping("/{matrNr}/add")
    public ResponseEntity<?> addGradeCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody GradeCourseMapping gradeCourseMapping, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.addGradeCourseToStudent(matrNr, gradeCourseMapping);
    }

    /**
     * addGradeCourseToStudent
     * get a grade course object of a studetn
     * @param matrNr String
     * @return ResoponesEntity
     */
    @GetMapping("/{matrNr}/getAll")
    public ResponseEntity<?> getAllGradeCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.getAllGradeCourseOfStudent(matrNr);
    }

    /**
     * Get the grade average of a Student by its MatrNr.
     * @param matrNr
     * @return
     */
    @GetMapping("/{matrNr}/getAverage")
    public ResponseEntity<?> getAverageByMatrNr(@PathVariable(value = "matrNr") String matrNr){
        return gradeCourseMappingService.getAverageByMatrNr(matrNr);
    }

    /**
     * getGradeCourseOFStudent
     * get the grade course mapping of a stundent and a specific couse
     * @param matrNr String matriculation number o Student
     * @param number String number of the curse
     * @return  ResponseEntity<?>
     */
    @GetMapping("/{matrNr}/{number}/get")
    public ResponseEntity<?> getGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.getGradeCourseOfStudent(matrNr, number);
    }

    /**
     * deleteGradeCourseOfStudent
     * delete Grade Course Mapping of a student
     * @param matrNr String matriculation number of student
     * @param number grade course mapping json
     * @return ResoponesEntity
     */
    @DeleteMapping("/{matrNr}/{number}/delete")
    public ResponseEntity<?> deleteGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.deleteGradeCourseOfStudent(matrNr, number);
    }
}
