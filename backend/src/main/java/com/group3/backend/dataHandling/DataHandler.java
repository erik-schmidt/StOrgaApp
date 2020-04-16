package com.group3.backend.dataHandling;

import com.group3.backend.model.InitDataCourse;
import com.group3.backend.model.InitDataFieldOfStudy;
import com.group3.backend.model.InitDataLectureDate;
import java.sql.Time;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class DataHandler {

    public DataHandler(){

    }

    public Set<InitDataFieldOfStudy> getInitValuesFieldsOfStudy(){
        Set<InitDataFieldOfStudy> fieldOfStudySet = loadFieldOfStudy();
        for(InitDataFieldOfStudy fieldOfStudy: fieldOfStudySet){
            fieldOfStudy.setCourseList(loadCourses(fieldOfStudy.getName(), fieldOfStudy));
        }
        return fieldOfStudySet;
    }

    private Set<InitDataFieldOfStudy> loadFieldOfStudy() {
        Set<InitDataFieldOfStudy> fieldOfStudySet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("backend/FieldsOfStudy.txt"))){
            String line = null;
            while(!(line = reader.readLine()).equals("")) {
                String[] k = line.split("#");
                InitDataFieldOfStudy fieldOfStudy = new InitDataFieldOfStudy(k[0],k[1],Integer.parseInt(k[2]), null);
                fieldOfStudySet.add(fieldOfStudy);
            }
        }
        catch(IOException e){
            //throw new IOException();
            // TODO: 14.04.2020 Exceptionhandling
            System.out.println("faultA");
        }
        catch (Exception e){
            System.out.println(e.toString()+"A");
        }

        /*InitDataFieldOfStudy a = new InitDataFieldOfStudy("AIB", "Bachelor of Science Angewandte Informatik", 1, null);
        Set<InitDataFieldOfStudy> fieldOfStudySet = new HashSet<>();
        fieldOfStudySet.add(a);*/
        return fieldOfStudySet;
    }

    private Set<InitDataCourse> loadCourses(String fieldOfStudyPref, InitDataFieldOfStudy initDatafieldOfStudy){
        Set<InitDataCourse> courseSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("backend/" + fieldOfStudyPref+"Courses.txt"))){
            String line = null;
            while(!(line = reader.readLine()).equals("")) {
                String[] k = line.split("#");
                InitDataCourse course = new InitDataCourse(k[0],k[1],k[2],Integer.parseInt(k[3]), initDatafieldOfStudy,null);
                course.setLectureDateList(loadCLectureDates(fieldOfStudyPref,course.getDescription(),course));
                courseSet.add(course);
            }
        }
        catch(IOException e){
            // TODO: 14.04.2020 Exceptionhandling
            System.out.println("faultB");
        }
        catch (Exception e){
            System.out.println(e.toString()+"B");
        }
        return courseSet;
    }

    private Set<InitDataLectureDate> loadCLectureDates(String fieldOfStudy, String course, InitDataCourse initDataCourse){
        Set<InitDataLectureDate> lectureDateSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("backend/" + fieldOfStudy+"_"+course+"_"+"LectureDates.txt"))){
            String line = null;
            while(!(line = reader.readLine()).equals("")) {
                String[] k = line.split("#");
                InitDataLectureDate lectureDate = new InitDataLectureDate();
                String[] startTimeString = k[1].split(":");
                LocalTime startTime = LocalTime.of(Integer.parseInt(startTimeString[0]),Integer.parseInt(startTimeString[1]),00,00);
                String[] finishTimeString = k[2].split(":");
                LocalTime finishTime = LocalTime.of(Integer.parseInt(startTimeString[0]),Integer.parseInt(startTimeString[1]),00,00);
                lectureDate = new InitDataLectureDate(lectureDate.getWeekdayByName(k[0]), Time.valueOf(startTime), Time.valueOf(finishTime), initDataCourse);
                lectureDateSet.add(lectureDate);
            }
        }
        catch(IOException e){
            // TODO: 14.04.2020 Exceptionhandling
            System.out.println("faultC");
        }
        catch (Exception e){
            System.out.println(e.toString()+"C");
        }
        return lectureDateSet;
    }


}
