package com.group3.backend;

import com.group3.backend.controller.CourseController;
import com.group3.backend.dataHandling.DataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    private static Logger logger = LoggerFactory.getLogger(BackendApplication.class);

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public static void main(String[] args) {

        try {
            new DataHandler().loadCourses();
            SpringApplication.run(BackendApplication.class, args);

        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
        }
    }

}
