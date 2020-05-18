package com.group3.authentication.authenticationserver.controller;

import com.group3.authentication.authenticationserver.model.Student;
import com.group3.authentication.authenticationserver.request.AuthenticationRequest;
import com.group3.authentication.authenticationserver.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity loginStudent(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.loginStudent(authenticationRequest);
    }

    @PostMapping("register")
    public ResponseEntity registerStudent(@RequestBody Student student){
        return authenticationService.registerStudent(student);
    }
}
