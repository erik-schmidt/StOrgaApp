package com.group3.backend.service;

import com.group3.backend.controller.TimeTableDateRequest;
import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.model.Student;
import com.group3.backend.model.TimeTableObject;
import com.group3.backend.repository.TimeTableObjectRepository;
import org.apache.tomcat.jni.Local;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    // TODO: 29.05.2020 doc and test   exceptions
    public ResponseEntity getAllTimeTableObjectsByFieldOfStudyAndSemester(String matrNr){
        try{
            checkMatriculationNumber(matrNr);
            Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
            String fieldOfStudyAndSemester = student.getFieldOfStudy() +""+ student.getCurrentSemester();
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByFieldOfStudySemester(fieldOfStudyAndSemester);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: 29.05.2020 doc and test   exceptions
    public ResponseEntity getAllTimeTableObjectsByCourseNumber(String courseNumber){
        try{
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByCourseNumber(courseNumber);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // TODO: 30.05.2020 get next x days from current day
    public ResponseEntity getAllTimeTableObjectsBetween(TimeTableDateRequest timeTableDateRequest){
        try{
            LocalDate startTime = timeTableDateRequest.getStartDate();
            LocalDate endTime = timeTableDateRequest.getEndDate();
            String matrNr = timeTableDateRequest.getMatrNr();
            Student student = null;
            LocalDate today = LocalDate.now();
            if(matrNr!= null){
                student = (Student)studentService.getStudentByNumber(matrNr).getBody();
            }
            if(timeTableDateRequest.getTimePeriod()!=null&&timeTableDateRequest.getTimePeriod()>0){
                if(startTime !=null){
                    if(matrNr!=null){
                        List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(timeTableDateRequest.getStartDate(),
                                startTime.plusDays(timeTableDateRequest.getTimePeriod()-1),student.getFieldOfStudy()+""+student.getCurrentSemester() );
                        return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                    }else {
                        List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(timeTableDateRequest.getStartDate(),
                                startTime.plusDays(timeTableDateRequest.getTimePeriod()-1));
                        return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                    }
                }else {
                    if(matrNr!=null){
                        List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(today,
                                today.plusDays(timeTableDateRequest.getTimePeriod()-1),student.getFieldOfStudy()+""+student.getCurrentSemester() );
                        return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                    }else {
                        List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(today,
                                today.plusDays(timeTableDateRequest.getTimePeriod()-1));
                        return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                    }
                }
            }
            else if(timeTableDateRequest.isCurrentWeek()){
                LocalDate start = null;
                LocalDate end = null;
                int todayWeekday = today.getDayOfWeek().getValue();
                switch (todayWeekday){
                    case 1:
                        start = today;
                        end = today.plusDays(6);
                        break;
                    case 2:
                        start = today.minusDays(1);
                        end = today.plusDays(5);
                        break;
                    case 3:
                        start = today.minusDays(2);
                        end = today.plusDays(4);
                        break;
                    case 4:
                        start = today.minusDays(3);
                        end = today.plusDays(3);
                        break;
                    case 5:
                        start = today.minusDays(4);
                        end = today.plusDays(2);
                        break;
                    case 6:
                        start = today.minusDays(5);
                        end = today.plusDays(1);
                        break;
                    case 7:
                        start = today.minusDays(6);
                        end = today;
                        break;
                    default:
                        return new ResponseEntity("Fehler beim berechnen der nächsten Woche", HttpStatus.BAD_REQUEST);
                }
                if(matrNr!=null){
                    List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(start,
                            end,student.getFieldOfStudy()+""+student.getCurrentSemester() );
                    return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                }
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(start, end);
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }
            else if(matrNr == null && startTime == null && endTime != null){ //search before enddate
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateEnd(timeTableDateRequest.getEndDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr == null && startTime != null && endTime == null){ // search after startdate
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStart(timeTableDateRequest.getStartDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr == null && startTime != null && endTime != null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(timeTableDateRequest.getStartDate(),
                        timeTableDateRequest.getEndDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr != null && startTime == null && endTime == null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByFieldOfStudySemester(student.getFieldOfStudy()+""+student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr != null && startTime == null && endTime != null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateEndCourseSemester(timeTableDateRequest.getEndDate(),
                        student.getFieldOfStudy()+""+student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr != null && startTime != null && endTime == null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartCourseSemester(timeTableDateRequest.getStartDate(),
                        student.getFieldOfStudy()+""+student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else if(matrNr != null && startTime != null && endTime != null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(timeTableDateRequest.getStartDate(),
                        timeTableDateRequest.getEndDate(),student.getFieldOfStudy()+""+student.getCurrentSemester() );
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else {
                return new ResponseEntity("Keine Werte zum suchen im Request gefunden", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

