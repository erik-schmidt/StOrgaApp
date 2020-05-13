package com.group3.authentication.authenticationserver.backupThings.controller;

import com.group3.authentication.authenticationserver.backupThings.exceptions.EntityNotFoundException;
import com.group3.authentication.authenticationserver.backupThings.model.StudentAccount;
import com.group3.authentication.authenticationserver.backupThings.request.AuthenticationRequest;
import com.group3.authentication.authenticationserver.backupThings.response.JWTTokenResponse;
import com.group3.authentication.authenticationserver.backupThings.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@RestController
@RequestMapping
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<JWTTokenResponse> createStudentAccount(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.generateJWTToken(request.getUsername(), request.getPassword()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerStudentAccount(@RequestBody StudentAccount studentAccount){
        authenticationService.registerStudentAccount(studentAccount);
        return new ResponseEntity<>("StudentAccountCreated", HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
