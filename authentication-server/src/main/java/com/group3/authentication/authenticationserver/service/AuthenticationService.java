package com.group3.authentication.authenticationserver.service;

import com.group3.authentication.authenticationserver.model.Student;
import com.group3.authentication.authenticationserver.repository.StudentRepository;
import com.group3.authentication.authenticationserver.request.AuthenticationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private JwtService jwtService;
    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(JwtService jwtService, StudentRepository studentRepository, PasswordEncoder passwordEncoder){
        this.jwtService = jwtService;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity loginStudent(AuthenticationRequest authenticationRequest){
        return new ResponseEntity(jwtService.generateJwt(authenticationRequest.getUsername()), HttpStatus.OK);
    }

    public ResponseEntity registerStudent(Student student){
        Student st = new Student();
        st.setMatrNr(student.getMatrNr());
        st.setStudentPrename(student.getStudentPrename());
        st.setStudentFamilyname(student.getStudentFamilyname());
        st.setFieldOfStudy(student.getFieldOfStudy());
        st.setCurrentSemester(student.getCurrentSemester());
        st.setUsername(student.getUsername());
        st.setPassword(passwordEncoder.encode(student.getPassword()));
        studentRepository.save(st);
        return new ResponseEntity("studnet registered", HttpStatus.OK);
    }

}
