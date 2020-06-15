package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.Course.CourseWithoutNumberException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingCourseNotFoundException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingStudentWithoutMappedCoursesException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingWithoutNumberException;
import com.group3.backend.exceptions.GradeFormatException;
import com.group3.backend.exceptions.Student.StudentDoesntMatchToMatrNrException;
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
import com.group3.backend.model.Course;
import java.util.Set;

@Service
public class GradeCourseMappingService extends CheckMatrNrClass {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public GradeCourseMappingService(StudentRepository studentRepository, CourseRepository courseRepository, GradeCourseMappingRepository gradeCourseMappingRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping(){return "reachable";}

    /**
     * Is used to add a {@link GradeCourseMapping} to a {@link Student}.
     * Is called by "/{matrNr}/add".
     * @param matrNr
     *                  The matrNr of the {@link Student} the {@link GradeCourseMapping} should be mapped to.
     * @param gradeCourseMapping
     *                              The {@link GradeCourseMapping} which should be mapped to the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link GradeCourseMapping} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> addGradeCourseToStudent(String matrNr, GradeCourseMapping gradeCourseMapping){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr");
            }
            Student st = studentRepository.findByMatrNr(matrNr);
            if(st==null){
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no student with the number " + matrNr);
            }
            gradeCourseMapping.setStudent(st);
            if(checkIfCourseExists(gradeCourseMapping.getCourseNumber())){
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The curse with number "+
                        gradeCourseMapping.getCourseNumber() + " already has a grade and is mapped for Student " + matrNr);
            }
            Set<GradeCourseMapping> gradeCourseMappingSet = st.getGradeCourseMappings();
            if(checkGradeCourse(gradeCourseMapping)){
                gradeCourseMappingSet.add(gradeCourseMapping);
            }
            st.setGradeCourseMappings(gradeCourseMappingSet);
            studentRepository.save(st);
            logger.info("New Grade to curs successfully added");
            return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMapping);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to get all {@link GradeCourseMapping} of a {@link Student}.
     * Is called by "/{matrNr}/get".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link GradeCourseMapping} objects for.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link GradeCourseMapping} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getAllGradeCourseOfStudent(String matrNr){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr");
            }
            Student st = studentRepository.findByMatrNr(matrNr);
            checkIfStudentHasMappedGradeCourses(st);
            Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(st);
            logger.info("Grade to course of student "+ matrNr + " successfully read");
            return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMappingSet);
        }catch (GradeCourseMappingStudentWithoutMappedCoursesException e) {
            return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMappingRepository.findAllByStudent(studentRepository.findByMatrNr(matrNr)));
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to get a specific {@link GradeCourseMapping} of a specific {@link Student}.
     * Is called by "/{matrNr}/get/{number}".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link GradeCourseMapping} of.
     * @param number
     *                  The number of the {@link GradeCourseMapping} you want from the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link GradeCourseMapping} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getGradeCourseOfStudent(String matrNr, String number){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr");
            }
            if(checkMatricularNumberIsFree(matrNr)){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + matrNr);
            }
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            checkIfCourseExists(number);
            Student st = studentRepository.findByMatrNr(matrNr);
            Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(st);
            for(GradeCourseMapping gradeCourseMapping : gradeCourseMappingSet){
                if(gradeCourseMapping.getCourseNumber().equals(number)){
                    return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMapping);
                }
            }
            logger.error("No grade for course number " + number + " in student "+ matrNr+ " saved");
            return ResponseEntity.status(HttpStatus.OK).body(getEmptyList("Course"));
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * Is used to delete a {@link GradeCourseMapping} from a {@link Student}.
     * Is called by "/{matrNr}/delete/{number}".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link GradeCourseMapping} of.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link GradeCourseMapping} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> deleteGradeCourseOfStudent(String matrNr, String number) {
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr");
            }
            if(checkMatricularNumberIsFree(matrNr)){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + matrNr);
            }
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            Student student = studentRepository.findByMatrNr(matrNr);
            Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(student);
            for (GradeCourseMapping gradeCourseMapping : gradeCourseMappingSet) {
                if (gradeCourseMapping.getCourseNumber().equals(number)) {
                    gradeCourseMappingRepository.delete(gradeCourseMapping);
                    return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMapping);
                }
            }
            logger.error("No grade for course number " + number + " in student "+ matrNr+ " saved");
            return ResponseEntity.status(HttpStatus.OK).body(getEmptyList("Course"));
        } catch (Exception e) {
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Is used to get the average of all grades from a specific {@link Student}.
     * Is called by "/{matrNr}/getAverage".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the average of.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          average in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getAverageByMatrNr(String matrNr){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr");
            }
            if(checkMatricularNumberIsFree(matrNr)){
                throw new StudentDoesntMatchToMatrNrException("There is no student with matriculation number: " + matrNr);
            }
            double average = 0;
            int counter = 0;
            Set<GradeCourseMapping> gradeCourseMappings = gradeCourseMappingRepository.findAllByStudentMatrNr(matrNr);
            for (GradeCourseMapping mapping : gradeCourseMappings){
                counter++;
                average = average + mapping.getGrade();
            }
            average = Math.round((average/counter)*100)/100;
            return ResponseEntity.status(HttpStatus.OK).body(average);
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Is used to check if the syntax of the {@link GradeCourseMapping} is valid.
     * @param gradeCourseMapping
     *                              The {@link GradeCourseMapping} you want to check.
     * @return
     *          Returns true if the syntax of the {@link GradeCourseMapping} is valid.
     * @throws Exception
     *                      Is thrown if the syntax of the {@link GradeCourseMapping} is not valid.
     */
    private boolean checkGradeCourse(GradeCourseMapping gradeCourseMapping) throws Exception{
        if(gradeCourseMapping.getCourseNumber() == null||gradeCourseMapping.getCourseNumber().equals("")){
            throw new GradeCourseMappingWithoutNumberException("Error: Course number can not be null");
        }
        if(gradeCourseMapping.getGrade()<1.0||gradeCourseMapping.getGrade()>5.0){
            throw new GradeFormatException("Error: Grade must be between 1 and 5");
        }
        try {
            if(!(checkIfCourseExists(gradeCourseMapping.getCourseNumber()))){
                throw new GradeCourseMappingCourseNotFoundException("There is no course with this number" + gradeCourseMapping.getCourseNumber());
            }
        }catch (Exception e){
            throw  e;
        }
        return true;
    }

    /**
     * Is used to check if a {@link Student} has mapped {@link GradeCourseMapping} objects.
     * @param student
     *                  The {@link Student} you want to check.
     * @return
     *          Returns true if the {@link Student} has at least one mapped {@link GradeCourseMapping}.
     * @throws Exception
     *                      Is thrown if the {@link Student} has no mapped {@link GradeCourseMapping} objects.
     */
    private boolean checkIfStudentHasMappedGradeCourses(Student student) throws GradeCourseMappingStudentWithoutMappedCoursesException{
        Set<GradeCourseMapping> gradeCourseMappingSet = student.getGradeCourseMappings();
        if(gradeCourseMappingSet.isEmpty()){
            logger.error("The student " + student.getMatrNr() + " has grades to courses mapped");
            throw new GradeCourseMappingStudentWithoutMappedCoursesException("The student "+ student.getMatrNr() + " has no mapped grades to courses");
        }
        return true;
    }

    /**
     * Is used to check if a {@link Course} with the given number exists.
     * @param number
     *                  The number you want to know if its used in a {@link Course}.
     * @return
     *          Returns true if the number is used by a {@link Course}.
     *          Returns false if not.
     * @throws Exception
     *                      Is thrown if no {@link Course} with this number could be found.
     */
    private boolean checkIfCourseExists(String number){
        try {
            if (number.trim().isEmpty()){
                throw new CourseWithoutNumberException("Error: No number is given!");
            }
            if(courseRepository.findByNumber(number)==null){
                return false;
            }
            return true;
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return false;
        }
    }

    /**
     * Is used to check if a matrNr is already used by a {@link Student}.
     * @param matrNr
     *                  The matrNr you want to check.
     * @return
     *          Returns true if the matrNr is free to use.
     *          Returns false if not.
     * @throws Exception
     *                      Is thrown if the matrNr is already used by a {@link Student}.
     */
    private boolean checkMatricularNumberIsFree(String matrNr){
        try{
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            Student st = studentRepository.findByMatrNr(matrNr);
            if(st == null){
                return true;
            }
            return false;
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return false;
        }
    }
}
