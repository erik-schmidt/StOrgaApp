package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.Course.CourseWithoutNumberException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingCourseNotFoundException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingStudentWithoutMappedCoursesException;
import com.group3.backend.exceptions.GradCourseMapping.GradeCourseMappingWithoutNumberException;
import com.group3.backend.exceptions.GradeFormatException;
import com.group3.backend.exceptions.Student.StudentDoesntMatchToMatrNrException;
import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Link;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
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
     * addGradeCourseToStudent
     * add a Grade course mapping to a student to identify his grades in the given subjects
     * @param matrNr string matricuatlion number of student
     * @param gradeCourseMapping GradeCourseMApping object
     * @return ResponseEntity<?>
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
            if(!checkIfCourseExists(gradeCourseMapping.getCourseNumber())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no course with number:  "+
                        gradeCourseMapping.getCourseNumber() + " available");
            }
            Set<GradeCourseMapping> gradeCourseMappingSet = st.getGradeCourseMappings();
            gradeCourseMappingSet.add(checkGradeCourse(gradeCourseMapping));
            st.setGradeCourseMappings(gradeCourseMappingSet);
            studentRepository.save(st);
            logger.info("New Grade to course successfully added");
            return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMapping);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * getGradeCourseofStudent
     * get the grade course mapping of a student
     * @param matrNr string matricuatlion number of student
     * @return ResponseEntity<?>
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
     * getGradeCourseOfStudent
     * get the grade course mapping of a student. Get back only one course with the given number
     * @param matrNr String Matriculation number of Student
     * @param number String number of the curse wich should be searched for
     * @return ResponseEntity<?>
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No grade for course number " + number + " in student "+ matrNr+ " saved");
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * deleteGradeCourseOfStudent
     * delete GradeCourse mapping of a student if exists
     * @param matrNr String of students matricuatlation number
     * @return ResponseEntity<?>
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No grade for course number " + number + " in student "+ matrNr+ " saved");
        } catch (Exception e) {
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Calculates the average for all grades of the Student with the given MatrNr.
     * @param matrNr
     * @return
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
            double counter = 0;
            Set<GradeCourseMapping> gradeCourseMappings = gradeCourseMappingRepository.findAllByStudentMatrNr(matrNr);
            for (GradeCourseMapping mapping : gradeCourseMappings){
                counter++;
                average += mapping.getGrade();
            }
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            average = average/counter;
            return ResponseEntity.status(HttpStatus.OK).body(twoDForm.format(average));
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * checkGradeCourse
     * check the object that the mapping fields are not null or empty and check if the grade is in the
     * range of 1 to 5
     * @param gradeCourseMapping Object
     * @return acceptable gradeCourseMapping object
     * @throws Exception
     */
    private GradeCourseMapping checkGradeCourse(GradeCourseMapping gradeCourseMapping) throws Exception{
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
        return gradeCourseMapping;
    }

    /**
     * checkIfStudentHasMappedGradeCourses
     * check if the student has mapped any courses for read them
     * @param student Student
     * @return Set<GradeCourseMapping>
     * @throws Exception
     */
    private void checkIfStudentHasMappedGradeCourses(Student student) throws GradeCourseMappingStudentWithoutMappedCoursesException{
        Set<GradeCourseMapping> gradeCourseMappingSet = student.getGradeCourseMappings();
        if(gradeCourseMappingSet.isEmpty()){
            logger.error("The student " + student.getMatrNr() + " has grades to courses mapped");
            throw new GradeCourseMappingStudentWithoutMappedCoursesException("The student "+ student.getMatrNr() + " has no mapped grades to courses");
        }
    }

    /**
     * checkIfCourseExists
     * check if there is a course with the given number in the database
     * @param number String
     * @return acceptable gradeCourseMapping object
     * @throws Exception
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
     * checkMatricularNumberIsFree
     * checks if a given Matriculation number is free or already used by another student
     * @param matrNr number to check
     * @return String available matriculation number
     * @throws Exception matriculation number Exception
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
