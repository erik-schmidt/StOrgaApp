package com.group3.backend.service;

import com.group3.backend.exceptions.CourseDescriptionAlreadyExistsException;
import com.group3.backend.exceptions.CourseNumberAlreadyExistsException;
import com.group3.backend.model.Course;
import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.GradeCourseMappingRepository;
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
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, GradeCourseMappingRepository gradeCourseMappingRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
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
        boolean courseNumberAlreadyExists = false;
        boolean courseDescriptionAlreadyExists = false;
        for (Course c : courseRepository.findAll()){
            if (course.getNumber().equals(c.getNumber())){
                courseNumberAlreadyExists = true;
            }
            if (course.getDescription().equals(c.getDescription())){
                courseDescriptionAlreadyExists = true;
            }
        }
        try{
            if (courseNumberAlreadyExists){
                throw new CourseNumberAlreadyExistsException("Coursenumber already exists in the repository");
            }
            if (courseDescriptionAlreadyExists){
                throw new CourseDescriptionAlreadyExistsException("Coursedescription already exists");
            }
            Course cs = new Course(course.getFieldOfStudy(),course.getNumber(), course.getDescription(),
                    course.getRoom(), course.getProfessor(), course.getEcts(), course.getKindOfSubject(), course.getReccomendedSemester(),
                    course.getStudyFocus());
            courseRepository.save(cs);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (CourseNumberAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (CourseDescriptionAlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Course deleteCourse(String number){
        Course course = courseRepository.findByNumber(number);
        courseRepository.delete(course);
        return course;
    }

    public double getGradeByMatrNrAndCourseNumber(String matrNr, String number){
        Student student = studentRepository.findByMatrNr(matrNr);
        Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(student);
        double grade = 0;
        for (GradeCourseMapping mapping : gradeCourseMappingSet){
            if (mapping.getCourseNumber().equals(number)){
                grade = mapping.getGrade();
            }
        }
        return grade;
    }

    public Course deleteCourseFromStudent(String number, String matrNr){
        Student student = studentRepository.findByMatrNr(matrNr);
        Set<Course> courseSet = student.getCourses();
        Course course = new Course();
        for (Course c:courseSet){
            if (c.getNumber().equals(number)){
                course = c;
            }
        }
        courseRepository.delete(course);
        return course;
    }


}


//for(Course c : courseRepository.findAll()){
//        if(c.getNumber().equals("420")){
//        courseRepository.delete(c);
//        }
//        }