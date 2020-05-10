package com.group3.backend.service;

import com.group3.backend.exceptions.GradeCourseException;
import com.group3.backend.exceptions.MatriculationNumberException;
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

import java.util.Set;

@Service
public class GradeCourseMappingService {

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
            gradeCourseMappingSet.add(checkGradeCourse(gradeCourseMapping));
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
     * getGradeCourseofStudent
     * get the grade course mapping of a student
     * @param matrNr string matricuatlion number of student
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> getAllGradeCourseOfStudent(String matrNr){
        try {
            Student st = studentRepository.findByMatrNr(matrNr);
            checkIfStudentHasMappedGradeCourses(st);
            Set<GradeCourseMapping> gradeCourseMappingSet = gradeCourseMappingRepository.findAllByStudent(st);
            logger.info("Grade to course of student "+ matrNr + " successfully read");
            return ResponseEntity.status(HttpStatus.OK).body(gradeCourseMappingSet);
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
            checkMatriculationNumber(matrNr);
            if(checkMatricularNumberIsFree(matrNr)){
                throw new MatriculationNumberException("There is no student with matriculation number: " + matrNr);
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
     * checkGradeCourse
     * check the object that the mapping fields are not null or empty and check if the grade is in the
     * range of 1 to 5
     * @param gradeCourseMapping Object
     * @return acceptable gradeCourseMapping object
     * @throws Exception
     */
    private GradeCourseMapping checkGradeCourse(GradeCourseMapping gradeCourseMapping) throws Exception{
        if(gradeCourseMapping.getCourseNumber() == null||gradeCourseMapping.getCourseNumber().equals("")){
            throw new GradeCourseException("Error: Course number can not be null");
        }
        if(gradeCourseMapping.getGrade()<1.0||gradeCourseMapping.getGrade()>5.0){
            throw new GradeCourseException("Error: Grade must be between 1 and 5");
        }
        try {
            if(!(checkIfCourseExists(gradeCourseMapping.getCourseNumber()))){
                throw new GradeCourseException("There is no course with this number" + gradeCourseMapping.getCourseNumber());
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
    private void checkIfStudentHasMappedGradeCourses(Student student) throws Exception{
        Set<GradeCourseMapping> gradeCourseMappingSet = student.getGradeCourseMappings();
        if(gradeCourseMappingSet.isEmpty()){
            logger.error("The student " + student.getMatrNr() + " has grades to courses mapped");
            throw  new GradeCourseException("The student "+ student.getMatrNr() + " has no mapped grades to courses");
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
        if(courseRepository.findByNumber(number)==null){
            return false;
        }
        return true;
    }

    /**
     * checkMatricularNumberIsFree
     * checks if a given Matriculation number is free or already used by another student
     * @param matrNr number to check
     * @return String available matriculation number
     * @throws Exception matriculation number Exception
     */
    private boolean checkMatricularNumberIsFree(String matrNr){
        Student st = studentRepository.findByMatrNr(matrNr);
        if(st == null){
            return true;
        }
        return false;
    }

    /**
     * checkMatriculationNumber
     * checks if the number only hold nummeric values and if the number is exactly 6 values ling
     * @param matrNr String
     * @return string matricular number
     * @throws Exception
     */
    private String checkMatriculationNumber(String matrNr) throws Exception{
        try{
            int matNumberInt = Integer.parseInt(matrNr);
            if(!(matrNr.length()==6)){
                throw new Exception("not 6 long");
            }
        }catch (Exception e) {
            if (e.getClass() == NumberFormatException.class) {
                throw new MatriculationNumberException("In matricular number are no letters allowed. " +
                        " Take care of the allowed length of 6 units");
            } else {
                throw new MatriculationNumberException("Matriculation Number has not the right length. " +
                        "It must be exactly 6 units long. Only numbers are allowes");
            }
        }
        return matrNr;
    }
}
