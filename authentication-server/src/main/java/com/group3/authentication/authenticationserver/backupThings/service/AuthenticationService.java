package com.group3.authentication.authenticationserver.backupThings.service;

import com.group3.authentication.authenticationserver.backupThings.model.StudentAccount;
import com.group3.authentication.authenticationserver.backupThings.repository.StudentAccountRepository;
import com.group3.authentication.authenticationserver.backupThings.response.JWTTokenResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@Service
public class AuthenticationService {

    private StudentAccountRepository studentAccountRepository;
    private JwtTokenService jwtTokenService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(StudentAccountRepository studentAccountRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder){
        this.studentAccountRepository = studentAccountRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public JWTTokenResponse generateJWTToken(String username, String password){
        return studentAccountRepository.findOneByUsername(username)
                .filter(account ->  passwordEncoder.matches(password, account.getPassword()))
                .map(account -> new JWTTokenResponse(jwtTokenService.generateToken(username)))
                .orElseThrow(() ->  new EntityNotFoundException("Account not found"));
    }

    public StudentAccount registerStudentAccount(StudentAccount studentAccount){
        StudentAccount account = new StudentAccount();
        account.setStudentPrename(studentAccount.getStudentPrename());
        account.setStudentFamilyname(studentAccount.getStudentFamilyname());
        account.setUsername(studentAccount.getUsername());
        account.setFieldOfStudy(studentAccount.getFieldOfStudy());
        account.setMatrNr(studentAccount.getMatrNr());
        account.setCurrentSemester(studentAccount.getCurrentSemester());
        account.setPassword(passwordEncoder.encode(studentAccount.getPassword()));
        studentAccountRepository.save(account);
        return account;
    }


}
