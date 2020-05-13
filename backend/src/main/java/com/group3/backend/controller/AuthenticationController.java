package com.group3.backend.controller;

import com.group3.backend.authenticationRequest.AuthenticationRequest;
import com.group3.backend.model.Student;
import com.group3.backend.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @RequestMapping("login")
    public ResponseEntity loginStudent(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.loginStudent(authenticationRequest);
    }

    @PostMapping
    @RequestMapping("register")
    public ResponseEntity registerStudent(@RequestBody Student student){
        return authenticationService.registerStudent(student);
    }
}
