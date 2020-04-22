package com.group3.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    private static Logger logger = LoggerFactory.getLogger(BackendApplication.class);
    public static void main(String[] args) {

        try {
            SpringApplication.run(BackendApplication.class, args);
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
        }
    }

}
