package com.group3.backend.dataHandling;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.model.TimeTableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class DataHandler {

    private final String PATH = "backend";
    //private final String AIBCOURSES_FILE = "AIBCoursesSPO.txt";
    private final String AIBCOURSES_FILE = "AIBCoursesSPOEnlarged.txt";
    private final String ADMIN_USER = "AdminUser.txt";
    private final String TIMETABLE = "splan_ss_2020_AIB.txt";
    private Logger logger = LoggerFactory.getLogger(DataHandler.class);

    public DataHandler(){
    }

    /**
     * lade a set of the default courses out of a file
     * @return Set of courses
     */
    public Set<Course> loadCourses(){
        Set<Course> courseSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(AIBCOURSES_FILE))){
            String line = reader.readLine();
            while (!(line.equals("###"))){
                if(!(line.equals(""))) {
                    String[] k = line.split("#");
                    String b = k[9];
                    double a = Double.parseDouble(b);
                    Course course = new Course(k[0], k[1], k[2], k[3], k[4], Integer.parseInt(k[5]), k[6], Integer.parseInt(k[7]), k[8], Double.parseDouble(k[9]), Double.parseDouble(k[10]), k[11]);
                    courseSet.add(course);
                }
                line = reader.readLine();
            }
            return courseSet;
        }catch (Exception e){
            logger.error("Error wihile loading inital course data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }

    /**
     * loads the administrator user out of the file
     * @return Student which represents the administrator
     */
    public Student loadAdminUser(){
        Student admin = new Student();
        try(BufferedReader reader = new BufferedReader(new FileReader(ADMIN_USER))){
            String line = reader.readLine();
            while (!(line.equals("###"))){
                if(!(line.equals(""))) {
                    String[] k = line.split("#");
                    admin.setMatrNr(k[0]);
                    admin.setStudentPrename(k[1]);
                    admin.setStudentFamilyname(k[2]);
                    admin.setFieldOfStudy(k[3]);
                    admin.setCurrentSemester(Integer.parseInt(k[4]));
                    admin.setUsername(k[5]);
                    admin.setPassword(k[6]);
                }
                line = reader.readLine();
            }
            return admin;
        }catch (Exception e){
            logger.error("Error wihile loading inital course data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }

    public List<TimeTableObject> loadTimeTable(){
        List<TimeTableObject> timeTableObjectArrayList = new ArrayList<>(2000);
        try(BufferedReader reader = new BufferedReader(new FileReader(TIMETABLE))){
            String line = reader.readLine();
            TimeTableObject timeTableObject = null;
            boolean wordStart = false;
            while (line!=null) {
                switch (line){
                    case "BEGIN:VEVENT":
                        wordStart = true;
                        timeTableObject = new TimeTableObject();
                        break;
                    case "END:VEVENT":
                        timeTableObjectArrayList.add(timeTableObject);
                        wordStart = false;
                        break;
                    default:
                        if(wordStart) {
                            timeTableObject = fillTimeTableObjectWithAttributes(timeTableObject, line);
                        }
                        break;
                }
                line = reader.readLine();
            }
            return timeTableObjectArrayList;
        }catch (Exception e){
            logger.error("Error wihile loading inital time table data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }

    private TimeTableObject fillTimeTableObjectWithAttributes(TimeTableObject timeTableObject, String line){
        if(line.contains("DTSTAMP")){
            return timeTableObject;
        }else if(line.contains("DTSTART")){
            String dateTime = line.substring(26);
            String[] seperateDateTime = dateTime.split("T");
            DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
            LocalDate date = LocalDate.parse(seperateDateTime[0].replace(":", ""),formatter);
            timeTableObject.setDate(date);
            DateTimeFormatter timef = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime startTime = LocalTime.parse(seperateDateTime[1].substring(0,2)+":"
                    +seperateDateTime[1].substring(2,4)+":"+seperateDateTime[1].substring(4),timef);
            timeTableObject.setStartTime(startTime);
        }else if(line.contains("DTEND")){
            String dateTime = line.substring(24);
            String[] seperateDateTime = dateTime.split("T");
            DateTimeFormatter timef = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime endTime = LocalTime.parse(seperateDateTime[1].substring(0,2)+":"
                    +seperateDateTime[1].substring(2,4)+":"+seperateDateTime[1].substring(4),timef);
            timeTableObject.setFinishTime(endTime);
        }else if(line.contains("SUMMARY")){
            String summary = line.substring(8);
            timeTableObject.setSummary(summary);
            if(line.contains("(")){
                String[] courseNumber = summary.split("\\(");
                timeTableObject.setCourseNumber(courseNumber[1].replace(")", ""));
            }
        }else if(line.contains("UID")){
            String uid = line.substring(4);
            timeTableObject.setUID(uid);
        }else if(line.contains("LOCATION")){
            String location = line.substring(9);
            timeTableObject.setLocation(location);
        }else if(line.contains("DESCRIPTION")){
            String[] description = line.replace("\\", "#").substring(12).split("#n");
            timeTableObject.setDescription(line.substring(12).replace("\\", "#").replace("#n", " "));
            // TODO: 30.05.2020 keine hardgecodeden AIB felder
            if(description[description.length-1].contains("AIB"))
            timeTableObject.setFieldOfStudySemester(description[description.length-1]);
        }
        return timeTableObject;
    }
}
