package com.group3.backend.controller;

import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AccessChecker {

    private JwtTokenService jwtTokenService;
    private StudentRepository studentRepository;

    private final String ADMIN_USER_MATRNR = "000000";
    private final String ADMIN_USER = "StorgaAdministrator";

    @Autowired
    public AccessChecker(JwtTokenService jwtTokenService, StudentRepository studentRepository) {
        this.jwtTokenService = jwtTokenService;
        this.studentRepository  = studentRepository;
    }

    /**
     * checks if an Acces token maches the student which information is asked for
     * @param matrNr matriculation number of the student
     * @param token jwt in header of request
     * @return true if token maches with request student
     */
    public boolean checkAccess(String matrNr, String token){
        String extractToken = token.substring(7, token.length());
        String username = jwtTokenService.getUsernameFromToken(extractToken);
        String usernameRepo = studentRepository.findByMatrNr(matrNr).getUsername();
        if(!username.equals(usernameRepo)||checkAdmin(token)){
            return true;
        }
        return false;
    }

    /**
     * checks if the token asks for the informations is the admin. admin can access everything
     * @param token jwt in header of request
     * @return true if token maches with request admin
     */
    public boolean checkAdmin(String token){
        String extractToken = token.substring(7, token.length());
        String username = jwtTokenService.getUsernameFromToken(extractToken);
        if(username == ADMIN_USER){
            return true;
        }
        return false;
    }
}
