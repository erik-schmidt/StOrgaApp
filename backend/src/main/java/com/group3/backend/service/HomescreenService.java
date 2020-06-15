package com.group3.backend.service;

import com.group3.backend.controller.TimeTableDateRequest;
import com.group3.backend.model.*;
import com.group3.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class HomescreenService {
    private Logger logger = LoggerFactory.getLogger(StudentService.class);
    private CalendarEntryRepository calendarEntryRepository;
    private CourseRepository courseRepository;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private LinkRepository linkRepository;
    private StudentRepository studentRepository;
    private TimeTableObjectService timeTableObjectService;
    private CourseService courseService;

    @Autowired
    public HomescreenService(CalendarEntryRepository calendarEntryRepository, CourseRepository courseRepository,
                             GradeCourseMappingRepository gradeCourseMappingRepository, LinkRepository linkRepository,
                             StudentRepository studentRepository, TimeTableObjectService timeTableObjectService,
                             CourseService courseService) {
        this.calendarEntryRepository = calendarEntryRepository;
        this.courseRepository = courseRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
        this.linkRepository = linkRepository;
        this.studentRepository = studentRepository;
        this.timeTableObjectService = timeTableObjectService;
        this.courseService = courseService;
    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping(){
        return "reachable";
    }

    public ResponseEntity getHomescreenItems(String matrNr){
        List<Homescreen> homescreenList = new ArrayList<>();
        homescreenList.add(getCourse(matrNr));
        homescreenList.add(getCalenderEntry());
        homescreenList.add(getCourseGradeMapping());
        homescreenList.add(getLinks());
        homescreenList.add(getNextLession(matrNr));
        return new ResponseEntity(homescreenList, HttpStatus.OK);
    }

    private Homescreen getCourse(String matrNr){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Beigetretene Kurse");
        Set<Course> courseSet = (Set<Course>)courseService.getStudentsCourses(matrNr).getBody();
        String courseInformation = "";
        for(Course course : courseSet){
            courseInformation+=course.getDescription();
            courseInformation+="\n";
        }if(courseSet.isEmpty()){
            homescreen.setData("Keinen Kursen beigetreten");
        }else {
            homescreen.setData(courseInformation);
        }

        return homescreen;
    }

    private Homescreen getCalenderEntry(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Kalendereintrag");
        if(calendarEntryRepository.count()>0) {
            int maxID = calendarEntryRepository.findCalendarEntryByMaxId();
            CalendarEntry calendarEntry = calendarEntryRepository.findById(maxID);
            homescreen.setData(calendarEntry.getName() + " " + calendarEntry.getEntryStartTime() + " " + calendarEntry.getDescription());
        }else {
            homescreen.setData("Keine Kalendereinträge vorhanden");
        }
        return homescreen;
    }

    private Homescreen getCourseGradeMapping(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Noteneintrag");
        if(gradeCourseMappingRepository.count()>0) {
            int maxID = gradeCourseMappingRepository.findMaxID();
            GradeCourseMapping gradeCourseMapping = gradeCourseMappingRepository.findById(maxID);
            homescreen.setData(courseRepository.findByNumber(gradeCourseMapping.getCourseNumber()).getNumber()+" "+
                    courseRepository.findByNumber(gradeCourseMapping.getCourseNumber()).getDescription() + " mit Note" +
                    gradeCourseMapping.getGrade() + " eingetragen");
        }else {
            homescreen.setData("Keine Noten einem Kurs zugeordnet");
        }
        return homescreen;
    }

    private Homescreen getLinks(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Links");
        if(linkRepository.count()>0) {
            int maxID = linkRepository.findMaxID();
            Link link = linkRepository.findById(maxID);
            homescreen.setData(link.getLinkDescription() + " " + link.getLink());
        }else {
            homescreen.setData("keine Links gespeichert");
        }
        return homescreen;
    }

    private Homescreen getNextLession(String matrNr){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Nächste Unterrichtsstunde");
        TimeTableDateRequest timeTableDateRequest = new TimeTableDateRequest();
        timeTableDateRequest.setStartDate(LocalDate.now());
        int couter = 1;
        timeTableDateRequest.setTimePeriod(couter);
        timeTableDateRequest.setMatrNr(matrNr);
        List<TimeTableObject> timeTableObjects = (List<TimeTableObject>)timeTableObjectService.getAllTimeTableByResponseEntity(timeTableDateRequest).getBody();
        int i = 0;
        boolean gotNextLession = false;
        boolean tomorrow = false;
        while(i<timeTableObjects.size()&&!gotNextLession){
            if(LocalTime.now().isBefore(timeTableObjects.get(i).getStartTime())){
                homescreen.setData(timeTableObjects.get(i).getCourseNumber() + " " + timeTableObjects.get(i).getDescription()
                        + " startet um " + timeTableObjects.get(i).getStartTime());
                gotNextLession= true;
            }
            if(tomorrow){
                homescreen.setData(timeTableObjects.get(i).getCourseNumber() + " " + timeTableObjects.get(i).getDescription()
                        + " startet um " + timeTableObjects.get(i).getStartTime());
                gotNextLession= true;
            }
            i++;
            if(i>timeTableObjects.size()-1&&gotNextLession==false){
                timeTableDateRequest.setStartDate(timeTableDateRequest.getStartDate().plusDays(1));
                timeTableObjects = (List<TimeTableObject>)timeTableObjectService.getAllTimeTableByResponseEntity(timeTableDateRequest).getBody();
                tomorrow = true;
                i = 0;
            }

        }
        return homescreen;
    }
}
