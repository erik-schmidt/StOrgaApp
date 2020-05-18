package com.group3.authentication.authenticationserver.service;

import com.group3.authentication.authenticationserver.exceptions.CurrentSemesterException;
import com.group3.authentication.authenticationserver.exceptions.MatriculationNumberException;
import com.group3.authentication.authenticationserver.exceptions.StudentNameException;
import com.group3.authentication.authenticationserver.exceptions.UsernameException;
import com.group3.authentication.authenticationserver.model.Student;
import com.group3.authentication.authenticationserver.repository.StudentRepository;
import com.group3.authentication.authenticationserver.request.AuthenticationRequest;
import com.group3.authentication.authenticationserver.response.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AuthenticationService {

    private JwtService jwtService;
    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(JwtService jwtService, StudentRepository studentRepository, PasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * login an registered student
     * @param authenticationRequest
     * @return Response Entity with http.ok and token if success otherwise return http.badrequest
     */
    public ResponseEntity loginStudent(AuthenticationRequest authenticationRequest){
        try{
            Student student = studentRepository.findOneByUsername(authenticationRequest.getUsername()).get();
            boolean a = passwordEncoder.matches(authenticationRequest.getPassword(), student.getPassword());
            if(!a||student==null){
                return new ResponseEntity("Password oder Benutzername falsch", HttpStatus.BAD_REQUEST);
            }
            JwtResponse jwtResponse = new JwtResponse(jwtService.generateJwt(authenticationRequest.getUsername()),student.getMatrNr());
            return new ResponseEntity(jwtResponse, HttpStatus.OK);
        }catch(Exception e){
            if(e instanceof NoSuchElementException){
                return new ResponseEntity("Password oder Benutzername falsch", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(e.getClass()+" "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * register a given student
     * @param student
     * @return Response Entity with http.ok and student object if success otherwise return http.badrequest
     */
    public ResponseEntity registerStudent(Student student) {
        Student st = new Student();
        try{
            String matriculationNumber = checkMatricularNumberIsFree(student.getMatrNr());
            st.setMatrNr(checkMatriculationNumber(matriculationNumber));
            st.setStudentPrename(checkName(student.getStudentPrename(), "Prename"));
            st.setStudentFamilyname(checkName(student.getStudentFamilyname(), "Familyname"));
            st.setFieldOfStudy(student.getFieldOfStudy());
            st.setCurrentSemester(checkCurrentSemester(student.getCurrentSemester()));
            st.setUsername(checkUsername(student.getUsername()));
            st.setPassword(passwordEncoder.encode(student.getPassword()));
            studentRepository.save(st);
            logger.info("Student: " + st.getMatrNr() + " " + st.getStudentPrename() + " " +
                    st.getStudentFamilyname() + " successfully saved");
            return new ResponseEntity(st, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getClass()+" "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * checks if a given username is already used. Usernames must be unique
     * @param username String
     * @return uniqe username
     */
    private String checkUsername(String username) throws Exception{
        Optional<Student> optional = studentRepository.findOneByUsername(username);
        if(optional.isPresent()){
            throw new UsernameException("Benutzername " +username+ " ist schon vergeben");
        }
        if(username.length()<2||username.length()>50){
            throw new UsernameException(username + " muss zwischen 2 und 50 Stellen lang sein");
        }
        return username;
    }

    /**
     * checkMatricularNumberIsFree
     * checks if a given Matriculation number is free or already used by another student
     * @param matrNr number to check
     * @return String available matriculation number
     * @throws Exception matriculation number Exception
     */
    private String checkMatricularNumberIsFree(String matrNr) throws Exception{
        Student st = studentRepository.findByMatrNr(matrNr);
        if(st == null){
            return matrNr;
        }
        throw new MatriculationNumberException("Matrikelnummer " + matrNr + " ist schon vergeben");
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
                throw new Exception("nicht genau 6 Stellen lang");
            }
        }catch (Exception e) {
            if (e.getClass() == NumberFormatException.class) {
                throw new MatriculationNumberException("In der Matrikelnummer sind keine Buchstaben und Sonderzeichen erlaubt. " +
                        " Die Nummer muss genau 6 Stellen lang sein");
            } else {
                throw new MatriculationNumberException("Matrikelnummer ist nicht genau 6 stellen lang. " +
                        "Es sind nur Zahlen erlaubt");
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
            throw new StudentNameException(kindOfname + " muss zwischen 2 und 50 Buchstaben lang sein");
        }
        boolean found = false;
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                found = true;
            }
            if (found) {
                throw new StudentNameException("Keine Buchstaben im " + kindOfname + " erlaubt");
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
            throw new CurrentSemesterException("Semester muss zwischen 1 und 15 sein");
        }
        return currentSemester;
    }

}
