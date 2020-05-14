package com.group3.backend.dataHandling;

import com.group3.backend.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class DataHandler {

    private final String AIBCOURSES_FILE = "C:\\Users\\chris\\Documents\\00_Karriere\\00_Studium_HHN_AI\\#47_SWLab2\\AIB_LABSWP_2020_SS_HHN_UniApp\\backend\\AIBCoursesSPO.txt";
    private Logger logger = LoggerFactory.getLogger(DataHandler.class);

    public DataHandler() {
    }

    public Set<Course> loadCourses(){
        Set<Course> courseSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(AIBCOURSES_FILE))){
            String line = reader.readLine();
            while (!(line.equals("###"))){
                if(!(line.equals(""))) {
                    String[] k = line.split("#");
                    Course course = new Course(k[0], k[1], k[2], k[3], k[4], Integer.parseInt(k[5]), k[6], Integer.parseInt(k[7]), k[8]);
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
}
