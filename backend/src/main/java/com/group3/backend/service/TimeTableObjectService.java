package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.model.TimeTableObject;
import com.group3.backend.repository.TimeTableObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeTableObjectService extends CheckMatrNrClass {

    private TimeTableObjectRepository timeTableObjectRepository;
    private StudentService studentService;
    private Logger logger = LoggerFactory.getLogger(TimeTableObject.class);

    @Autowired
    public TimeTableObjectService(TimeTableObjectRepository timeTableObjectRepository, StudentService studentService){
        this.timeTableObjectRepository = timeTableObjectRepository;
        this.studentService = studentService;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "reachable"
     */
    public String ping(){
        return "reachable";
    }

    public ResponseEntity getAllTimeTableObjectsByFieldOfStudyAndSemester(String matrNr){
        return null;
    }

    public ResponseEntity getAllTimeTableObjectsByStartTime(LocalDateTime date){
        return null;
    }

    public ResponseEntity getAllTimeTableObjectsByStartEndTime(LocalDateTime startDate, LocalDateTime endDate){
        return null;
    }

    public ResponseEntity getAllTimeTableObjectsByCourseNumber(String courseNumber){
        return null;
    }
}
