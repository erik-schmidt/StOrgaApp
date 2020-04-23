package com.group3.backend.service;

import com.group3.backend.exceptions.CurrentSemesterException;
import com.group3.backend.exceptions.GradeCourseException;
import com.group3.backend.exceptions.MatriculationNumberException;
import com.group3.backend.exceptions.StudentNameException;
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
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository, GradeCourseMappingRepository gradeCourseMappingRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    public String ping(){
        return "reachable";
    }

    /**
     * getAllStudnets
     * return a List of all Students saved in the Database
     * @return List<Student>
     */
    public ResponseEntity<?> getAllStudents(){
        try{
            List<Student> studentList = studentRepository.findAll();
            if(studentList.isEmpty()){
                logger.error("Error while reading all Students: There are no students saved");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There are no students saved");
            }
            logger.info("Students successffully read");
            return ResponseEntity.status(HttpStatus.OK).body(studentList);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * getStudentByNumber
     * return a Student with the given number
     * @param matNr
     * @return Student
     */
    public ResponseEntity<?> getStudentByNumber(@PathVariable(value = "matNr") String matNr){
        try{
            checkMatriculationNumber(matNr);
            if(checkMatricularNumberIsFree(matNr)){
                throw new MatriculationNumberException("There is no student with matriculation number: " + matNr);
            }
            Student st = studentRepository.findByMatrNr(matNr);
            logger.info("Student: " + matNr + " successffully read");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    /**
     * createStudent
     * create a student in the Database if it not exists already
     * @param student
     * @return ResponseEntity<String> if succesfull return id of student
     */
    public ResponseEntity<?> createStudent(Student student){
        Student st = new Student();
        try {
            if(!(checkMatricularNumberIsFree(student.getMatrNr()))){
                throw new MatriculationNumberException("Matricular number " + student.getMatrNr() + "already used");
            }
            checkMatricularNumberIsFree(student.getMatrNr());
            st.setMatrNr(checkMatriculationNumber(student.getMatrNr()));
            st.setStudentPrename(checkName(student.getStudentPrename(), "Prename"));
            st.setStudentFamilyname(checkName(student.getStudentFamilyname(), "Familyname"));
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(checkCurrentSemester(student.getCurrentSemester()));
            studentRepository.saveAndFlush(st);
            logger.info("Student: " + st.getMatrNr() + " " + st.getStudentPrename() + " " +
                    st.getStudentFamilyname() + " successfully saved");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " : "+ e.getMessage());
        }
    }

    /**
     * deleteStudent
     * delete a Student with the giben matriculation number out of the database
     * @param matNr
     * @return Student object
     */
    public ResponseEntity<?> deleteStudent(String matNr){
        try{
            checkMatriculationNumber(matNr);
            if(checkMatricularNumberIsFree(matNr)){
                throw new MatriculationNumberException("There is no student with matriculation number: " + matNr);
            }
            Student st = studentRepository.findByMatrNr(matNr);
            studentRepository.delete(st);
            logger.info("Student: " + matNr + " successffully deleted");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }

    public ResponseEntity<?> updateStudent(Student student) {
        try {
            if(checkMatricularNumberIsFree(student.getMatrNr())){
                throw new MatriculationNumberException("There is no student with matriculation number: " + student.getMatrNr());
            }
            Student st = studentRepository.findByMatrNr(student.getMatrNr());
            //st.setMatrNr(student.getMatrNr());
            st.setStudentPrename(student.getStudentPrename());
            st.setStudentFamilyname(student.getStudentFamilyname());
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(student.getCurrentSemester());
            studentRepository.save(st);
            logger.info("Student: " + student.getMatrNr() +" " + student.getStudentPrename() +" "+ student.getStudentFamilyname() + " successffully updated");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
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
            logger.info("New Grade to cours successfully added");
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
        // TODO: 23.04.2020 exception handling and REfactor
        try {
            Student st = studentRepository.findByMatrNr(matrNr);
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
        // TODO: 23.04.2020 Exception handling and refactor
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
        // TODO: 23.04.2020 Exception handling and refactor
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
     * checkIfStudentHasMappedGradeCourses
     * check if the student has mapped any courses for read them
     * @param student Student
     * @return Set<GradeCourseMapping>
     * @throws Exception
     */
    private Set<GradeCourseMapping> checkIfStudentHasMappedGradeCourses(Student student) throws Exception{
        Set<GradeCourseMapping> gradeCourseMappingSet = student.getGradeCourseMappings();
        if(gradeCourseMappingSet.isEmpty()){
            logger.error("The student " + student.getMatrNr() + " has grades to courses mapped");
            throw  new GradeCourseException("The student "+ student.getMatrNr() + " has no mapped grades to courses");
        }
        return gradeCourseMappingSet;
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

    /**
     * checkName
     * checks if the name have the right size (2 to 50 letters) and has no numeric values inside
     * @param name String
     * @param kindOfname String
     * @return string name
     * @throws StudentNameException
     */
    private String checkName(String name, String kindOfname) throws Exception {
        if(name.length()<2||name.length()>50){
            throw new StudentNameException(kindOfname + " must be between 2 an 50 letters");
        }
        boolean found = false;
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                found = true;
            } else if (found) {
                throw new StudentNameException(" Numbers are not allowed in " + kindOfname );
            }
        }
        return name;
    }

    /**
     * checkCurrentSemester
     * checks if the semester value is between 1 and 15
     * @param currentSemester
     * @return int currentSemester value
     * @throws CurrentSemesterException
     */
    private int checkCurrentSemester(int currentSemester)throws Exception{
        if(currentSemester<1||currentSemester>15){
            throw new CurrentSemesterException("Semester can not be smaller then zero and not be bigger than 15");
        }
        return currentSemester;
    }
}
