package com.group3.authentication.authenticationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class AuthenticationServerApplication
{

    @Bean
    public PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
    }
    public static void main( String[] args ) {
        SpringApplication.run(AuthenticationServerApplication.class, args);
    }
}

