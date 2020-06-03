package com.group3.backend.dataHandling;

import com.group3.backend.model.Course;
import com.group3.backend.model.News;
import com.group3.backend.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class DataHandler {

    //private final String PATH = "C:\\Users\\chris\\Documents\\00_Karriere\\00_Studium_HHN_AI\\#47_SWLab2\\AIB_LABSWP_2020_SS_HHN_UniApp\\backend\\";
    private final String PATH = "";
    //private final String AIBCOURSES_FILE = "AIBCoursesSPO.txt";
    private final String AIBCOURSES_FILE = "AIBCoursesSPOEnlarged.txt";
    private final String ADMIN_USER = "AdminUser.txt";
    private final String NEWS_FILE = "News.txt";
    private Logger logger = LoggerFactory.getLogger(DataHandler.class);

    public DataHandler() {
    }

    /**
     * lade a set of the default courses out of a file
     *
     * @return Set of courses
     */
    public Set<Course> loadCourses() {
        Set<Course> courseSet = new HashSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH+AIBCOURSES_FILE))){
            String line = reader.readLine();
            while (!(line.equals("###"))) {
                if (!(line.equals(""))) {
                    String[] k = line.split("#");
                    String b = k[9];
                    double a = Double.parseDouble(b);
                    Course course = new Course(k[0], k[1], k[2], k[3], k[4], Integer.parseInt(k[5]), k[6],
                            Integer.parseInt(k[7]), k[8], Double.parseDouble(k[9]), Double.parseDouble(k[10]), k[11]);
                    courseSet.add(course);
                }
                line = reader.readLine();
            }
            return courseSet;
        } catch (Exception e) {
            logger.error("Error wihile loading inital course data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }

    /**
     * loads the administrator user out of the file
     *
     * @return Student which represents the administrator
     */
    public Student loadAdminUser() {
        Student admin = new Student();
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH+ADMIN_USER))){
            String line = reader.readLine();
            while (!(line.equals("###"))) {
                if (!(line.equals(""))) {
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
        } catch (Exception e) {
            logger.error("Error wihile loading inital course data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }

    public Set<News> loadNews() {
        Set<News> newsSet = new HashSet();
        try (BufferedReader reader = new BufferedReader(new FileReader( NEWS_FILE))) {
            String line = reader.readLine();
            while (!(line.equals("###"))) {
                if (!(line.equals(""))) {
                    String[] k = line.split("#");
                    News news = new News(k[0], k[1], k[2], Date.valueOf(k[3]));
                    newsSet.add(news);
                }
                line = reader.readLine();
            }
            return newsSet;
        } catch (Exception e) {
            logger.error("Error while loading inital news data: " + e.getClass() + " " + e.getMessage());
        }
        return null;
    }
}
