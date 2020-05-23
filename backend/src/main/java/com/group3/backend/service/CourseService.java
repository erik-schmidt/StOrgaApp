package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.Course.*;
import com.group3.backend.exceptions.MatrNrWrongLengthException;
import com.group3.backend.exceptions.NoDescriptionException;
import com.group3.backend.exceptions.NoMatrNrException;
import com.group3.backend.model.Course;
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
public class CourseService extends CheckMatrNrClass {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private StudentService studentService;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
    }

    /**
     * check if the system is reachable
     * @return String
     */
    public String ping() {
        return "reachable";
    }

    /**
     * get All courses from the Database
     * @return ResponseEntity<List<Courses>> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getAllCourses(){
        try{
            List<Course> courseList = courseRepository.findAll();
            if(courseList.isEmpty()){
                logger.error("Error while reading all courses: There are no courses saved");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are not courses saved");
            }
            return ResponseEntity.status(HttpStatus.OK).body(courseList);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * get all courses of a student with the given matriculation number
     * @param matrNr String
     * @return ResponseEntity<Set<Courses>> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getStudentsCourses( String matrNr){
        try{
            if (!checkMatriculationNumber(matrNr)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong MatrNr format!");
            }
            //Student student = studentRepository.findByMatrNr(matrNr);
            if((studentService.getStudentByNumber(matrNr).getBody().getClass()==String.class)){
                return studentService.getStudentByNumber(matrNr);
            }else {
                Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
                Set<Course> courses = student.getCourses();
                return ResponseEntity.status(HttpStatus.OK).body(courses);
            }
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * get a course by it's number
     * @param number
     * @return ResponseEntity<Course> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> getCourseByNumber(String number){
        try{
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            Course cs = courseRepository.findByNumber(number);
            if(cs == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no course with this numbe r" +number+" in the system");
            }
            return ResponseEntity.status(HttpStatus.OK).body(cs);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * a studnet can enter a course by adding the yours as an attribute
     * @param matrNr
     * @param course
     * @return
     */
    public ResponseEntity<?> addCourseToStudent(String matrNr,  Course course){
        Course course1 = null;
        try {
            if (!checkMatriculationNumber(matrNr)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong MatrNr format!");
            }
            if (!checkCourse(course)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the course object!");
            }
            Student student = studentRepository.findByMatrNr(matrNr);
            course1 = courseRepository.findByNumber(course.getNumber());
            Set<Student> studentSet = new HashSet<>();
            studentSet.add(student);
            course1.setStudents(studentSet);
            courseRepository.save(course1);
            courseRepository.saveAndFlush(course1);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(course1);
    }

    /**
     * create a new course and save in db
     * @param course Course
     * @return ResponseEntity<Course> if successfull, otherwiese ResponseEntity<String> with error message
     */
    public ResponseEntity<?> createCourse(Course course){
        try{
            if (!checkCourse(course)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the course object!");
            }
            for (Course c : courseRepository.findAll()){
                if (course.getNumber().equals(c.getNumber())||course.getDescription().equals(c.getDescription())){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coursenumber already exists in the repository");
                }
            }
                Course cs = new Course(course.getFieldOfStudy(),course.getNumber(), course.getDescription(),
                        course.getRoom(), course.getProfessor(), course.getEcts(), course.getKindOfSubject(), course.getRecommendedSemester(),
                        course.getStudyFocus(), course.getWorkingHoursInClass(), course.getWorkingHoursSelf(), course.getKindOfExam());
                courseRepository.save(cs);
                return ResponseEntity.status(HttpStatus.OK).body(cs);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * delete courses out of the repository
     * @param number
     * @return
     */
    public ResponseEntity<?> deleteCourse(String number){
        try{
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            Course course = courseRepository.findByNumber(number);
            if(course == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no course with this numbe r" +number+" in the system");
            }else {
                courseRepository.delete(course);
            }
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Returns a List of Courses, selected by their kindOfSubject.
     * @param kindOfSubject
     * @return
     */
    public ResponseEntity<?> getCourseByKindOfSubject(String kindOfSubject){
        try{
            if (kindOfSubject.trim().isEmpty()){
                throw new CourseWithoutKindOfSubjectException("Error: No kind of subject is given!");
            }
            List<Course> courses = courseRepository.findAllByKindOfSubject(kindOfSubject);
            if (courses.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no courses with this kind of subject in the system.");
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(courses);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Returns a List of Courses, selected by their studyFocus.
     * @param studyFocus
     * @return
     */
    public ResponseEntity<?> getCourseByStudyFocus(String studyFocus){
        try{
            if (studyFocus.trim().isEmpty()){
                throw new CourseWithoutStudyFocusException("Error: No study focus is given!");
            }
            List<Course> courses = courseRepository.findAllByKindOfSubject(studyFocus);
            if (courses.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no courses with this study focus in the system.");
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(courses);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /*public ResponseEntity<?> getGradeByMatrNrAndCourseNumber(String matrNr, String number){
        Student student = studentRepository.findByMatrNr(matrNr);
        Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(student);
        double grade = 0;
        for (GradeCourseMapping mapping : gradeCourseMappingSet){
            if (mapping.getCourseNumber().equals(number)){
                grade = mapping.getGrade();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(grade);
    }*/

    /**
     * delete a course student mapping. the student can leave the entered courses
     * @param number
     * @param matrNr
     * @return
     */
    public ResponseEntity<?> deleteCourseFromStudent(String number, String matrNr) {
        try{
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            if (matrNr.trim().isEmpty()){
                throw new NoMatrNrException("Error: No MatrNr is given!");
            }
            if (!checkMatriculationNumber(matrNr)){
                throw new MatrNrWrongLengthException("Error: MatrNr not matches the format!");
            }
            if ((studentService.getStudentByNumber(matrNr).getBody().getClass() == String.class)) {
                return studentService.getStudentByNumber(matrNr);
            } else {
                try {
                    Student student = (Student) studentService.getStudentByNumber(matrNr).getBody();
                    Set<Course> courseSet = student.getCourses();
                    Course course = new Course();
                    for (Course c : courseSet) {
                        if (c.getNumber().equals(number)) {
                            course = c;
                        }
                    }
                    courseRepository.delete(course);
                    courseSet = student.getCourses();
                    return ResponseEntity.status(HttpStatus.OK).body(courseSet);
                }catch (Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
                }
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    public boolean checkCourse(Course course){
        try{
            if (course.getNumber().trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number in the course!");
            }
            if (course.getDescription().trim().isEmpty()){
                throw new NoDescriptionException("Error: No description in the course!");
            }
            if (course.getEcts() < 1){
                throw new CourseWithZeroECTSException("Error: The course has zero or negative ECTs!");
            }
            if (course.getFieldOfStudy().trim().isEmpty()){
                throw new CourseWithoutFieldOfStudyException("Error: The course has no field of study!");
            }
            if (course.getKindOfSubject().trim().isEmpty()){
                throw new CourseWithoutKindOfSubjectException("Error: The course has no kind of subject!");
            }
            if (course.getProfessor().trim().isEmpty()){
                throw new CourseWithoutProfessorException("Error: The course has no professor!");
            }
            if (course.getRecommendedSemester() > 1 || course.getRecommendedSemester()>10){
                throw new CourseWithoutRecommendedSemesterException("Error: The course has no valid recommended semester!");
            }
            if (course.getStudyFocus().trim().isEmpty()){
                throw new CourseWithoutStudyFocusException("Error: The course has no study focus!");
            }
            if (course.getRoom().trim().isEmpty()){
                throw new CourseWithoutRoomException("Error: The course has no room!");
            }
            return true;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return false;
        }
    }
}
