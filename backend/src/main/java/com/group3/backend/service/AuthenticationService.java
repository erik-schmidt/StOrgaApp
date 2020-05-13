package com.group3.backend.service;

import com.group3.backend.authenticationJwtService.JwtService;
import com.group3.backend.authenticationRequest.AuthenticationRequest;
import com.group3.backend.authenticationResponse.JwtResponse;
import com.group3.backend.model.Student;
import com.group3.backend.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;


@Service
public class AuthenticationService {

    private StudentService studentService;

    public AuthenticationService(StudentService studentService){
        this.studentService = studentService;
    }

    public ResponseEntity loginStudent(AuthenticationRequest authenticationRequest){
        return studentService.loginStudent(authenticationRequest);
    }

    public ResponseEntity registerStudent(Student student){
        return studentService.createStudent(student);
    }

}
