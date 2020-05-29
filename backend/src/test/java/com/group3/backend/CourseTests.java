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
import java.util.Random;
import java.util.Set;

@SpringBootTest
class CourseTests {

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Random random;

    @Autowired
    public CourseTests(){
        random = new Random();
    }

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
        Student student = createDummyStudent();
        studentService.createStudent(student);
        for (Course c: (List<Course>)courseService.getAllCourses().getBody()){
            courseService.addCourseToStudent("202481", c);
        }
        Assertions.assertEquals(((Set<Course>)courseService.getStudentsCourses("202481").getBody()).size(), ((List<Course>) courseService.getAllCourses().getBody()).size());
    }

    private Course createDummyCourse(){
        Course course = new Course();
        course.setFieldOfStudy("");
        course.setNumber("345876");
        course.setDescription("Dummy-Course");
        course.setRoom("A420");
        course.setProfessor("Mr.Dummy");
        course.setEcts(5);
        course.setKindOfSubject("WahlPflichtfach");
        course.setRecommendedSemester(6);
        course.setStudyFocus("Allgemein");
        course.setWorkingHoursInClass(20.0);
        course.setWorkingHoursSelf(40.0);
        course.setKindOfExam("Schein");
        return course;
    }

    private Student createDummyStudent(){
        Student student = new Student();
        student.setMatrNr("202481");
        student.setStudentPrename("Liyan");
        student.setStudentFamilyname("Fu-Wacker");
        student.setFieldOfStudy("AIB");
        student.setCurrentSemester(7);
        student.setUsername("LiyanFuW");
        student.setPassword(passwordEncoder.encode("ladsfklajsfl505"));
        return student;
    }

}