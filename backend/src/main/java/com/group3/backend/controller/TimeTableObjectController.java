package com.group3.backend.controller;

import com.group3.backend.service.TimeTableObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/timeTable")
@CrossOrigin()
public class TimeTableObjectController {

    private AccessChecker accessChecker;
    private TimeTableObjectService timeTableObjectService;

    @Autowired
    public TimeTableObjectController(AccessChecker accessChecker, TimeTableObjectService timeTableObjectService){
        this.accessChecker = accessChecker;
        this.timeTableObjectService = timeTableObjectService;
    }

    /**
     * return a String with a successful message if backend reachable
     * @return String "reachable"
     */
    @GetMapping("/ping")
    public String ping(){
        return timeTableObjectService.ping();
    }

    /**
     * return a list of all time table objects of a given students field of study and semester
     * @param matrNr number of a logged in student
     * @param token String token of a logged in student
     * @return
     */
    @GetMapping("/{matNr}/getAllByFieldOfStudyAndSemester")
    private ResponseEntity getAllTimeTableObjectsByFieldOfStudyAndSemester(@PathVariable(value = "matNr") String matrNr,  @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsByFieldOfStudyAndSemester(matrNr);
    }

    /**
     * return a list of all time table objcets at a specific day;
     * @param matrNr String number of a logged in student
     * @param date day you want to search the time table objects
     * @param token String token of a logged in student
     * @return ResponseEntity List with all courses of thos day. Or ResponseEntity String in case of error or null objects found
     */
    @GetMapping("/{matNr}/{date}/getAllByStartTime")
    private ResponseEntity getAllTimeTableObjectsByStartTime(@PathVariable(value = "matNr") String matrNr, @PathVariable(value = "date") LocalDate date, @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsByStartTime(date);
    }

    /**
     * return a list of all time table objects between start and end date
     * @param matrNr number of a logged in student
     * @param startDate Start date
     * @param endDate End date
     * @param token String token of a logged in student
     * @return ResponseEntity List with all courses of thos day. Or ResponseEntity String in case of error or null objects foun
     */
    @GetMapping("/{matNr}/{startDate}/{endDate}/getAllByStartEndTime")
    private ResponseEntity getAllTimeTableObjectsByStartEndTime(@PathVariable(value = "matNr") String matrNr, @PathVariable(value = "startDate") LocalDateTime startDate, @PathVariable(value = "endDate") LocalDateTime endDate, @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsByStartEndTime(startDate, endDate);
    }

    /**
     * find the dates of a course with the given number
     * @param matrNr number of a logged in student
     * @param courseNumber course you want to get the timetableobjects of
     * @param token String token of a logged in student
     * @return ResponseEntity List with all courses of thos day. Or ResponseEntity String in case of error or null objects found
     */
    @GetMapping("/{matNr}/{courseNumber}/getAllByCourseNumber")
    private ResponseEntity getAllTimeTableObjectsByCourseNumber(@PathVariable(value = "matNr") String matrNr, @PathVariable(value = "courseNumber") String courseNumber, @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsByCourseNumber(courseNumber);
    }


    /*@GetMapping("/{matNr}/getAllByStudent")
    private ResponseEntity getAllTimeTableObjectsByStudent(@PathVariable(value = "matNr") String matrNr, @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsByStudent(matrNr);
    }*/

    /**
     * returns all timetableobjects between a start and enddate. the dates are representet by the {@link TimeTableDateRequest} objects
     * @param matrNr number of a logged in student
     * @param timeTableDateRequest Holds LocalDate of start and Enddate
     * @param token String token of a logged in student
     * @return
     */
    @GetMapping("/{matNr}/getAllBetween")
    private ResponseEntity getAllTimeTableObjectsBetween(@PathVariable(value = "matNr") String matrNr, @RequestBody TimeTableDateRequest timeTableDateRequest, @RequestHeader(name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return timeTableObjectService.getAllTimeTableObjectsBetween(timeTableDateRequest.getStartDate(), timeTableDateRequest.getEndDate());
    }
}
