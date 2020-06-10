package com.group3.backend.controller;

import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.group3.backend.model.Student;

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
     * Checks if an access token matches the {@link Student} which the information are asked for.
     * @param matrNr
     *              Matriculation number of the {@link Student}.
     * @param token
     *              Jwt in header of request.
     * @return
     *              True if token matches with request {@link Student}.
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
     * Checks if the token asks for the information is the admin. Admin has access to everything.
     * @param token
     *              Jwt in header of request.
     * @return
     *              True if the token matches with request admin.
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
