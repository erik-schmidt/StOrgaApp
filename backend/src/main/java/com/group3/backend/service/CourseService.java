package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.Course.*;
import com.group3.backend.exceptions.MatrNrWrongLengthException;
import com.group3.backend.exceptions.NoDescriptionException;
import com.group3.backend.exceptions.MatrNrException;
import com.group3.backend.model.Course;
import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.GradeCourseMappingRepository;
import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository, StudentService studentService,
                         GradeCourseMappingRepository gradeCourseMappingRepository, PasswordEncoder passwordEncoder,
                         JwtTokenService jwtTokenService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.studentService = studentService;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping() {
        return "reachable";
    }

    /**
     * Is used to get all {@link Course} objects from the repository.
     * Called by "/get".
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
     * Is used to get all {@link Course} objects of a {@link Student}.
     * Called by "/{matrNr}/get".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Course} objects from.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getStudentsCourses(String matrNr){
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
     * Is used to get the {@link Course} with the specific number.
     * Called by "/get/{number}".
     * @param number
     *                  The number of the {@link Course} you want to get.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
     * Is used to add a {@link Course} to a {@link Student}.
     * Called by "/{matrNr}/add".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to add the {@link Course} to.
     * @param course
     *                  The {@link Course} you want to add to the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> addCourseToStudent(String matrNr,  Course course){
        Course course1 = null;
        try {
            if (!checkMatriculationNumber(matrNr)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong MatrNr format!");
            }
//            if (!checkCourse(course)){
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the course object!");
//            }
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
     * Is used to create a new {@link Course} and save it in the repository.
     * @param course
     *                  The {@link Course} you want to add to the repository.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the saved
     *          {@link Course} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> createCourse(Course course){
        try{
            if (!checkCourse(course)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in the course object!");
            }
            for (Course c : courseRepository.findAll()){
                if (course.getNumber().equals(c.getNumber())||course.getDescription().equals(c.getDescription())){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Course number already exists in the repository");
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
     * Is used to returns a list of {@link Course} objects, selected by their kindOfSubject.
     * Called by "/get/{kindOfSubject}".
     * @param kindOfSubject
     *                      The kindOfSubject you want to search for.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
     * Is used to return a list of {@link Course} objects, selected by their studyFocus.
     * Called by "/get/{studyFocus}".
     * @param studyFocus
     *                      The studyFocus you want to search for.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Course} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
     * Is used to delete a {@link Course} from a {@link Student}.
     * Called by "/{matrNr}/delete/{number}".
     * @param number
     *                  The number of the {@link Course} you want to delete from the {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to remove the {@link Course} from.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          remaining {@link Course} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> deleteCourseFromStudent(String matrNr, String number) {
        try{
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            if (matrNr.trim().isEmpty()){
                throw new MatrNrException("Error: No MatrNr is given!");
            }
            if (!checkMatriculationNumber(matrNr)){
                throw new MatrNrWrongLengthException("Error: MatrNr not matches the format!");
            }
            if ((studentService.getStudentByNumber(matrNr).getBody().getClass() == String.class)) {
                return studentService.getStudentByNumber(matrNr);
            } else {
                try {
                    Student student = (Student) studentService.getStudentByNumber(matrNr).getBody();
                    Course course = courseRepository.findByNumber(number);

                    Set<Course> studentCourses = student.getCourses();
                    if(studentCourses == null || studentCourses.isEmpty()){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kurse mit Nummer"+ number + "ist nicht im student "+ number + "gespeichert");
                    }
                    for (Course c : studentCourses) {
                        if (c.getNumber().equals(number)) {
                            studentCourses.remove(c);
                        }
                    }
                    Set<Student> courseStudents = course.getStudents();
                    if(courseStudents==null||courseStudents.isEmpty()){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kurse mit Nummer"+ number + "ist nicht im student "+ number + "gespeichert");
                    }
                    for (Student s : courseStudents) {
                        if (s.getMatrNr().equals(matrNr)) {
                            courseStudents.remove(s);
                        }
                    }
                    student.setCourses(studentCourses);
                    course.setStudents(courseStudents);
                    studentService.updateStudent(student);
                    courseRepository.save(course);
                    return ResponseEntity.status(HttpStatus.OK).body(studentCourses);
                }catch (Exception e){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
                }
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Method to check if the syntax of a {@link Course} is valid.
     * @param course
     *                  Returns true if syntax of the {@link Course} is valid.
     * @return
     *          Returns true if the syntax is valid and false if not.
     */
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
            if (course.getRecommendedSemester() < 1 || course.getRecommendedSemester() > 10){
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
