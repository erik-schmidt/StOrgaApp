package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.service.CourseService;
import com.group3.backend.service.GradeCourseMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.backend.model.Student;

@RestController
@RequestMapping("/course")
@CrossOrigin()
public class CourseController {

    private CourseService courseService;
    private GradeCourseMappingService gradeCourseMappingService;
    private AccessChecker accessChecker;

    @Autowired
    public CourseController(CourseService courseService, GradeCourseMappingService gradeCourseMappingService, AccessChecker accessChecker) {
        this.courseService = courseService;
        this.gradeCourseMappingService = gradeCourseMappingService;
        this.accessChecker = accessChecker;
    }

    // TODO: 24.04.2020 search by course number not by description

    /**
     * The ping-method of this controller. It is used to check if the frontend is able to access the methods of this
     * controller.
     * @return  Returns the String "reachable" if access to the methods is possible.
     */
    @GetMapping("/ping")
    public String ping(){
        return courseService.ping();
    }

    /**
     * The get-method to get all {@link Course} in the {@link Course}-Repository.
     * @param token
     *              The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    // TODO: 02.06.2020 authentifizierung
    @GetMapping("/get")
    public ResponseEntity<?> getAllCourses(@RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return courseService.getAllCourses();
    }

    /**
     * The get-method to get a specific {@link Course} by its number.
     * @param number
     *                  The number of the {@link Course} object you want to get.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    // TODO: 02.06.2020 authentifizierung
    @GetMapping("/get/{number}")
    public ResponseEntity<?> getCourseByNumber(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number,  @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.getCourseByNumber(number);
    }

    /**
     * The get-method to get a specific {@link Course} by its kindOfSubject.
     * @param kindOfSubject
     *                      The kindOfSubject of the {@link Course} object you want to get.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    // TODO: 02.06.2020 authentifizierung
    @GetMapping("/{matrNr}/get/{kindOfSubject}")
    public ResponseEntity<?> getCoursesByKindOfSubject(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "kindOfSubject") String kindOfSubject,  @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.getCourseByKindOfSubject(kindOfSubject);
    }

    /**
     * The get-method to get a specific {@link Course} by its studyFocus.
     * @param studyFocus
     *                      The studyFocus of the {@link Course} object you want to get.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    // TODO: 02.06.2020 authentifizierung
    @GetMapping("/{matrNr}/get/{studyFocus}")
    public ResponseEntity<?> getCoursesByStudyFocus(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "studyFocus") String studyFocus, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.getCourseByStudyFocus(studyFocus);
    }

    /**
     * The get-method to get all {@link Course} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Course} entries for.
     * @param token
     *              The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getStudentsCourses(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.getStudentsCourses(matrNr);
    }

    /**
     * The add-method to add a specific {@link Course} to a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to add the {@link Course} to.
     * @param course
     *                  The {@link Course} object you want to add to the {@link Student}.
     * @param token
     *              The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @PutMapping("/{matrNr}/add")
    public ResponseEntity<?> addCourseToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Course course, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.addCourseToStudent(matrNr, course);
    }

    /**
     * Method to delet a specific course of a specific student.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link Course} of.
     * @param number
     *                  The number of the {@link Course} you want to delete.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a Set of
     *          {@link Course} objects which remain at the {@link Student}. Therefore the deleted {@link Course}
     *          shouldn't be under those {@link Course} objects from the Set. Otherwise, pls tell it the Backend.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @DeleteMapping("/{matrNr}/delete/{number}")
    public ResponseEntity<?> deleteCourseFromStudent(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "number") String number, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return courseService.deleteCourseFromStudent(matrNr, number);
    }
}
