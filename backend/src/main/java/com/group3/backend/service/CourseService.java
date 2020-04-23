package com.group3.backend.service;

import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public String ping(){
        return "reachable";}

    public List<Course> getAllCourses(){
        List<Course> courseList = courseRepository.findAll();
        return courseList;
    }

    public Set<Course> getStudentsCourses( String matrNr){
        Student student = studentRepository.findByMatrNr(matrNr);
        return student.getCourses();
    }

    public Course getCourseByNumber( String number){
        Course cs = courseRepository.findByNumber(number);
        return cs;
    }

    public ResponseEntity<Course> addCourseToStudent(String matrNr,  Course course){
        try {
            Student student = studentRepository.findByMatrNr(matrNr);
            Course course1 = courseRepository.findByNumber(course.getNumber());
            Set<Student> studentSet = new HashSet<>();
            studentSet.add(student);
            course1.setStudents(studentSet);
            courseRepository.save(course1);
            courseRepository.saveAndFlush(course1);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Course> createCourse(Course course){
        Course cs = new Course(course.getFieldOfStudy(),course.getNumber(), course.getDescription(),
                course.getRoom(), course.getProfessor(), course.getEcts(), course.getKindOfSubject(), course.getReccomendedSemester(),
                course.getStudyFocus());
        courseRepository.save(cs);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Course deleteCourse(String number){
        Course course = courseRepository.findByNumber(number);
        courseRepository.delete(course);
        return course;
    }


}
