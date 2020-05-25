package com.group3.backend;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.service.CourseService;
import com.group3.backend.service.StudentService;
import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.transaction.BeforeTransaction;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class CourseTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeTransaction
    void init(){
        List<Course> courseList;
        courseList = (List<Course>)courseService.getAllCourses().getBody();
        System.out.println("This are the courses in the repository:");
        for(Course c:courseList){
            System.out.println(c.getNumber());
        }
    }

    @Test
    @Transactional
    void testAddingAndDeletingCourse() {
        courseService.createCourse(new Course("AIB", "420", "SWELab", "A007", "Prof.Haag", 7, "choose", 9, "test", 20.0, 50.0, "Klausur und lehrbegleitend"));
        boolean newCourseIsInRepository = false;
        for(Course c : (List<Course>)courseService.getAllCourses().getBody()){
            if(c.getNumber().equals("420")){
                newCourseIsInRepository = true;
            }
        }
        Assertions.assertTrue(newCourseIsInRepository);
        System.out.println("Adding course works!");
        //Now test for deleting a course.
        courseService.deleteCourse("420");
        for(Course c : (List<Course>)courseService.getAllCourses().getBody()){
            if(c.getNumber().equals("420")){
                newCourseIsInRepository = false;
            }
        }
        Assertions.assertTrue(newCourseIsInRepository);
        System.out.println("Deleting course works!");
    }

    @Test
    void testGetAndSetCoursesOfStudent(){
        Student student0 = new Student();
        student0.setMatrNr("202481");
        student0.setStudentPrename("Liyan");
        student0.setStudentFamilyname("Fu-Wacker");
        student0.setFieldOfStudy("AIB");
        student0.setCurrentSemester(7);
        student0.setUsername("LiyanFuW");
        student0.setPassword(passwordEncoder.encode("ladsfklajsfl505"));
        studentService.createStudent(student0);
        for (Course c: (List<Course>)courseService.getAllCourses().getBody()){
            courseService.addCourseToStudent("202481", c);
        }
        Assertions.assertEquals(((Set<Course>)courseService.getStudentsCourses("202481").getBody()).size(), ((List<Course>) courseService.getAllCourses().getBody()).size());
    }

//    @Test
//    void testGettingAllCourses(){
//        int index = 1;
//        for (Course c: courseService.getAllCourses()){
//            System.out.println(index + c.getDescription());
//            index++;
//        }
//        System.out.println("There are " + courseService.getAllCourses().size() + " courses in the repository.");
//        Assertions.assertTrue(courseService.getAllCourses().size()==20);
//    }



}