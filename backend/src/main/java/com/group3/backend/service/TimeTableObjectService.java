package com.group3.backend.service;

import com.group3.backend.controller.TimeTableDateRequest;
import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.model.TimeTableObject;
import com.group3.backend.repository.TimeTableObjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TimeTableObjectService extends CheckMatrNrClass {

    private TimeTableObjectRepository timeTableObjectRepository;
    private CourseService courseService;
    private StudentService studentService;
    private Logger logger = LoggerFactory.getLogger(TimeTableObject.class);

    @Autowired
    public TimeTableObjectService(TimeTableObjectRepository timeTableObjectRepository, StudentService studentService, CourseService courseService){
        this.timeTableObjectRepository = timeTableObjectRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }
// TODO: 01.06.2020 exception handling -> student might only see courses he joined.
    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "reachable"
     */
    public String ping(){
        return "reachable";
    }

    /**
     * get all time table objects of a field of study and semester
     * @param matrNr represents a sutdent and allows to get the field of study and semester which should be searched for
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
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

    /**
     * get all time table objects with a given course number
     * @param courseNumber String course number of a course
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
    public ResponseEntity getAllTimeTableObjectsByCourseNumber(String courseNumber){
        try{
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByCourseNumber(courseNumber);
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get all time table objects by attributes of the {@link TimeTableDateRequest}
     * @param timeTableDateRequest Holds search attributes which might be null or empty
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
    public ResponseEntity getAllTimeTableByResponseEntity(TimeTableDateRequest timeTableDateRequest){
        try{
            Student student = null;
            if(timeTableDateRequest.getMatrNr()!= null){
                student = (Student)studentService.getStudentByNumber(timeTableDateRequest.getMatrNr()).getBody();
            }
            if(timeTableDateRequest.getTimePeriod()!=null&&timeTableDateRequest.getTimePeriod()>0){
                return getTimeTableByPeriod(timeTableDateRequest.getStartDate(), student, timeTableDateRequest);
            }
            else if(timeTableDateRequest.isCurrentWeek()){
                return getTimeTableByCurrentWeek(student);
            }else{
                return getTimeTableByAttributes(timeTableDateRequest.getStartDate(), timeTableDateRequest.getEndDate(), timeTableDateRequest, student);
            }
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * search time table objects in a specific period
     * @param startTime startdate for search. If start date is null start search at todays date
     * @param student student wich allow us to search time table objects also by field of study and semester
     * @param timeTableDateRequest Holds search attributes which might be null or empty
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
    private ResponseEntity getTimeTableByPeriod(LocalDate startTime, Student student, TimeTableDateRequest timeTableDateRequest){
        if(startTime !=null){
            if(timeTableDateRequest.getMatrNr()!=null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(timeTableDateRequest.getStartDate(),
                        startTime.plusDays(timeTableDateRequest.getTimePeriod()-1),student.getFieldOfStudy()+""+student.getCurrentSemester() );
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else {
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(timeTableDateRequest.getStartDate(),
                        startTime.plusDays(timeTableDateRequest.getTimePeriod()-1));
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }
        }else {
            if(timeTableDateRequest.getMatrNr()!=null){
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(LocalDate.now(),
                        LocalDate.now().plusDays(timeTableDateRequest.getTimePeriod()-1),student.getFieldOfStudy()+""+student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }else {
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(LocalDate.now(),
                        LocalDate.now().plusDays(timeTableDateRequest.getTimePeriod()-1));
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }
        }
    }

    /**
     * search all time table objects of the current week
     * @param student student wich allow us to search time table objects also by field of study and semester
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
    private ResponseEntity getTimeTableByCurrentWeek(Student student){
        LocalDate start = LocalDate.now().minusDays((LocalDate.now().getDayOfWeek().getValue()) - ((LocalDate.now().getDayOfWeek().getValue()-1)));
        LocalDate end = LocalDate.now().plusDays(7 -(LocalDate.now().getDayOfWeek().getValue()));
        if(student.getMatrNr()!=null){
            List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(start,
                    end,student.getFieldOfStudy()+""+student.getCurrentSemester());
            return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
        }
        List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(start, end);
        return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
    }

    /**
     * search time table objects by the following attributes.
     * @param startTime If it is null, ignore
     * @param endTime If it is null, ignore
     * @param timeTableDateRequest Holds search attributes which might be null or empty
     * @param student student wich allow us to search time table objects also by field of study and semester
     * @return RensponsEntity<TimeTableObject> which might be null or RensponsEntity<String> in case of error
     */
    private ResponseEntity getTimeTableByAttributes(LocalDate startTime, LocalDate endTime, TimeTableDateRequest timeTableDateRequest, Student student){
        if(timeTableDateRequest.getMatrNr() == null) {
            if (startTime == null && endTime != null) {
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateEnd(timeTableDateRequest.getEndDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            } else if (startTime != null && endTime == null) {
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStart(timeTableDateRequest.getStartDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            } else if (startTime != null && endTime != null) {
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEnd(timeTableDateRequest.getStartDate(),
                        timeTableDateRequest.getEndDate());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }
        } else if(timeTableDateRequest.getMatrNr() != null) {
            if (startTime == null && endTime == null) {
                if(timeTableDateRequest.isOnlyJoinedCourses()){
                    Set<Course> courses = (Set)courseService.getStudentsCourses(timeTableDateRequest.getMatrNr()).getBody();
                    if(courses==null){
                        new ResponseEntity("Student "+ timeTableDateRequest.getMatrNr() +" ist in keiem Kurs beigetreten", HttpStatus.BAD_REQUEST);
                    }
                    List<TimeTableObject> timeTableObjectList = new ArrayList<>();
                    for(Course c : courses){
                        List<TimeTableObject> timeTableObjects = timeTableObjectRepository.findAllByCourseNumberAndFieldOfStudySemester(c.getNumber(), student.getFieldOfStudy()+student.getCurrentSemester());
                        for(TimeTableObject t : timeTableObjects){
                            timeTableObjectList.add(t);
                        }
                    }
                    return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                }
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByFieldOfStudySemester(student.getFieldOfStudy() + "" + student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            } else if (startTime == null && endTime != null) {
                if(timeTableDateRequest.isOnlyJoinedCourses()){
                    Set<Course> courses = (Set)courseService.getStudentsCourses(timeTableDateRequest.getMatrNr()).getBody();
                    if(courses==null){
                        new ResponseEntity("Student "+ timeTableDateRequest.getMatrNr() +" ist in keiem Kurs beigetreten", HttpStatus.BAD_REQUEST);
                    }
                    List<TimeTableObject> timeTableObjectList = new ArrayList<>();
                    for(Course c : courses){
                        List<TimeTableObject> timeTableObjects = timeTableObjectRepository.findAllByDateEndCourse(c.getNumber(), endTime, student.getFieldOfStudy() + "" + student.getCurrentSemester()) ;
                        for(TimeTableObject t : timeTableObjects){
                            timeTableObjectList.add(t);
                        }
                    }
                    return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                }
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateEndCourseSemester(timeTableDateRequest.getEndDate(),
                        student.getFieldOfStudy() + "" + student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            } else if (startTime != null && endTime == null) {
                if(timeTableDateRequest.isOnlyJoinedCourses()){
                    Set<Course> courses = (Set)courseService.getStudentsCourses(timeTableDateRequest.getMatrNr()).getBody();
                    if(courses==null){
                        new ResponseEntity("Student "+ timeTableDateRequest.getMatrNr() +" ist in keiem Kurs beigetreten", HttpStatus.BAD_REQUEST);
                    }
                    List<TimeTableObject> timeTableObjectList = new ArrayList<>();
                    for(Course c : courses){
                        List<TimeTableObject> timeTableObjects = timeTableObjectRepository.findAllByDateStartCourse(c.getNumber(), startTime, student.getFieldOfStudy() + "" + student.getCurrentSemester()) ;
                        for(TimeTableObject t : timeTableObjects){
                            timeTableObjectList.add(t);
                        }
                    }
                    return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                }
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartCourseSemester(timeTableDateRequest.getStartDate(),
                        student.getFieldOfStudy() + "" + student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            } else if (startTime != null && endTime != null) {
                if(timeTableDateRequest.isOnlyJoinedCourses()){
                    Set<Course> courses = (Set)courseService.getStudentsCourses(timeTableDateRequest.getMatrNr()).getBody();
                    if(courses==null){
                        new ResponseEntity("Student "+ timeTableDateRequest.getMatrNr() +" ist in keiem Kurs beigetreten", HttpStatus.BAD_REQUEST);
                    }
                    List<TimeTableObject> timeTableObjectList = new ArrayList<>();
                    for(Course c : courses){
                        List<TimeTableObject> timeTableObjects = timeTableObjectRepository.findAllByDateStartEndCourse(c.getNumber(), startTime, endTime,student.getFieldOfStudy() + "" + student.getCurrentSemester()) ;
                        for(TimeTableObject t : timeTableObjects){
                            timeTableObjectList.add(t);
                        }
                    }
                    return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
                }
                List<TimeTableObject> timeTableObjectList = timeTableObjectRepository.findAllByDateStartEndCourseSemester(timeTableDateRequest.getStartDate(),
                        timeTableDateRequest.getEndDate(), student.getFieldOfStudy() + "" + student.getCurrentSemester());
                return new ResponseEntity(timeTableObjectList, HttpStatus.OK);
            }
        }
        return new ResponseEntity("Keine Werte zum suchen im Request gefunden", HttpStatus.BAD_REQUEST);
    }
}

