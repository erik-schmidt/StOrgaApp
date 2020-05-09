package com.group3.authentication.authenticationserver.service;

import com.group3.authentication.authenticationserver.model.StudentAccount;
import com.group3.authentication.authenticationserver.repository.StudentAccountRepository;
import com.group3.authentication.authenticationserver.response.JWTTokenResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

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
        account.setFirstname(studentAccount.getFirstname());
        account.setLastname(studentAccount.getLastname());
        account.setUsername(studentAccount.getUsername());
        account.setMatriculationNumber(studentAccount.getMatriculationNumber());
        account.setPassword(passwordEncoder.encode(studentAccount.getPassword()));
        studentAccountRepository.save(account);
        return account;
    }


}
