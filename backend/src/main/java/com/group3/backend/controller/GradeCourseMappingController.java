package com.group3.backend.controller;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.service.GradeCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.backend.model.Student;

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
     * The ping-method of this controller. It is used to check if the frontend is able to access the methods of this
     * controller.
     * @return  Returns the String "reachable" if access to the methods is possible.
     */
    @GetMapping("/ping")
    public String ping(){return gradeCourseMappingService.ping();}

    /**
     * The add-method to add a {@link GradeCourseMapping} to a {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to add the {@link GradeCourseMapping} to.
     * @param gradeCourseMapping
     *                              The {@link GradeCourseMapping} you want to add to the {@link Student}.
     * @param token
     *              The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link GradeCourseMapping} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @PutMapping("/{matrNr}/add")
    public ResponseEntity<?> addGradeCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody GradeCourseMapping gradeCourseMapping, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.addGradeCourseToStudent(matrNr, gradeCourseMapping);
    }

    /**
     * The get-method to get all {@link GradeCourseMapping} objects of a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link GradeCourseMapping} objects of.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          requested {@link GradeCourseMapping} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getAllGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.getAllGradeCourseOfStudent(matrNr);
    }

    /**
     * The get-method to get the average of all grades of a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the average for.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the average
     *          of all grades of the {@link Student} as a double in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/getAverage")
    public ResponseEntity<?> getAverageByMatrNr(@PathVariable(value = "matrNr") String matrNr){
        return gradeCourseMappingService.getAverageByMatrNr(matrNr);
    }

    /**
     * The get-method to get a specific {@link GradeCourseMapping} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link GradeCourseMapping} for.
     * @param number
     *                  The number of the {@link GradeCourseMapping} you want from the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     *            Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *            requested {@link GradeCourseMapping} objects in its body.
     *            If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get/{number}")
    public ResponseEntity<?> getGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.getGradeCourseOfStudent(matrNr, number);
    }

    /**
     * The delete-method to delete a {@link GradeCourseMapping} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link GradeCourseMapping} of.
     * @param number
     *                  The number of the {@link GradeCourseMapping} you want to delete from the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     */
    @DeleteMapping("/{matrNr}/delete/{number}")
    public ResponseEntity<?> deleteGradeCourseOfStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return gradeCourseMappingService.deleteGradeCourseOfStudent(matrNr, number);
    }
}
