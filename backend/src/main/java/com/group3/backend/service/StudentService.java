package com.group3.backend.service;

import com.group3.backend.exceptions.CurrentSemesterException;
import com.group3.backend.exceptions.MatriculationNumberException;
import com.group3.backend.exceptions.StudentNameException;
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
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private GradeCourseMappingRepository gradeCourseMappingRepository;
    private Logger logger = LoggerFactory.getLogger(StudentService.class);
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository,
                          GradeCourseMappingRepository gradeCourseMappingRepository, PasswordEncoder passwordEncoder,
                          JwtTokenService jwtTokenService) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.gradeCourseMappingRepository = gradeCourseMappingRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "reachable"
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
    public ResponseEntity<?> getStudentByNumber(String matNr, String token){
        String extractToken = token.substring(7, token.length());
        String username =jwtTokenService.getUsernameFromToken(extractToken);
        String usernameRepo = studentRepository.findByMatrNr(matNr).getUsername();
        if(!username.equals(usernameRepo)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
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
                throw new MatriculationNumberException("Matriculation number " + student.getMatrNr() + " already used");
            }
            checkMatricularNumberIsFree(student.getMatrNr());
            st.setMatrNr(checkMatriculationNumber(student.getMatrNr()));
            st.setStudentPrename(checkName(student.getStudentPrename(), "Prename"));
            st.setStudentFamilyname(checkName(student.getStudentFamilyname(), "Familyname"));
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(checkCurrentSemester(student.getCurrentSemester()));
            st.setUsername(student.getUsername());
            st.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.saveAndFlush(st);
            logger.info("Student: " + st.getMatrNr() + " " + st.getStudentPrename() + " " +
                    st.getStudentFamilyname() + " successfully saved");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " "+ e.getMessage());
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

    /**
     * updateStudent
     * update the students attributes
     * @param student Student
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> updateStudent(Student student) {
        try {
            if(checkMatricularNumberIsFree(student.getMatrNr())){
                throw new MatriculationNumberException("There is no student with matriculation number: " + student.getMatrNr());
            }
            Student st = studentRepository.findByMatrNr(student.getMatrNr());
            //Matriculation number should not be changed form student -> only set
            //st.setMatrNr(student.getMatrNr());
            st.setStudentPrename(checkName(student.getStudentPrename(), "Prename"));
            st.setStudentFamilyname(checkName(student.getStudentFamilyname(), "Familyname"));
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(checkCurrentSemester(student.getCurrentSemester()));
            studentRepository.save(st);
            logger.info("Student: " + student.getMatrNr() +" " + student.getStudentPrename() +" "+ student.getStudentFamilyname() + " successffully updated");
            return ResponseEntity.status(HttpStatus.OK).body(st);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
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
                        "It must be exactly 6 units long. Only numbers are allowed");
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
            }
            if (found) {
                throw new StudentNameException("Numbers are not allowed in " + kindOfname );
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
            throw new CurrentSemesterException("Semester can not be smaller then 1 and not be bigger than 15");
        }
        return currentSemester;
    }


    /*public ResponseEntity loginStudent(AuthenticationRequest authenticationRequest){
        JwtResponse response = studentRepository.findOneByUsername(authenticationRequest.getUsername())
                .filter(account ->  passwordEncoder.matches(authenticationRequest.getPassword(), account.getPassword()))
                .map(account -> new JwtResponse(jwtService.generateJwt(authenticationRequest.getUsername())))
                .orElseThrow(() ->  new EntityNotFoundException("Account not found"));
        return new ResponseEntity(response, HttpStatus.OK);
    }*/
}
