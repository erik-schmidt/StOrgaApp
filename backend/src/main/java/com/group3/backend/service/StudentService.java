package com.group3.backend.service;

import com.group3.backend.exceptions.CurrentSemesterException;
import com.group3.backend.exceptions.MatriculationNumberException;
import com.group3.backend.exceptions.StudentNameException;
import com.group3.backend.model.Student;
import com.group3.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class StudentService {


    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
    public List<Student> getAllStudents(){
        List<Student> studentList = studentRepository.findAll();
        return studentList;
    }

    /**
     * getStudentByNumber
     * return a Student with the given number
     * @param matNr
     * @return Student
     */
    public Student getStudentByNumber(@PathVariable(value = "matNr") String matNr){
        Student st = studentRepository.findByMatrNr(matNr);
        return st;
    }

    /**
     * createStudent
     * create a student in the Database if it not exists already
     * @param student
     * @return ResponseEntity<String> if succesfull return id of student
     */
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        Student st = new Student();
        try {
            st.setMatrNr(checkMatriculationNumber(student.getMatrNr()));
            st.setStudentPrename(checkName(student.getStudentPrename(), "Prename"));
            st.setStudentFamilyname(checkName(student.getStudentFamilyname(), "Familyname"));
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(checkCurrentSemester(student.getCurrentSemester()));
        } catch (Exception e){
            logger.error(e.getClass() +" " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " : "+ e.getMessage());
        }

        try {
            studentRepository.saveAndFlush(st);
            logger.info("Student: " + st.toString() + " successfully saved in Database");
        } catch (Exception e){
            logger.error(e.getClass() +" " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " : "+ e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(st.getMatrNr());
        //return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * deleteStudent
     * delete a Student with the giben matriculation number out of the database
     * @param matNr
     * @return Student object
     */
    public Student deleteStudent(@PathVariable(value = "matNr") String matNr){
        Student st = studentRepository.findByMatrNr(matNr);
        studentRepository.delete(st);
        return st;
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
