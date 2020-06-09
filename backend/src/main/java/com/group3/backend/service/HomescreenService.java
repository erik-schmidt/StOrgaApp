package com.group3.backend.service;

import com.group3.backend.model.Homescreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomescreenService {
    private Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    public HomescreenService(){

    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping(){
        return "reachable";
    }

    public ResponseEntity getHomescreenItems(String matrNr){
        List<Homescreen> homescreenList = new ArrayList<>();
        homescreenList.add(getCalenderEntry());
        homescreenList.add(getCourseGradeMapping());
        homescreenList.add(getLinks());
        homescreenList.add(getNextLession());

        return new ResponseEntity(homescreenList, HttpStatus.OK);
    }

    private Homescreen getCalenderEntry(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Kalendereintrag");

        return homescreen;
    }

    private Homescreen getCourseGradeMapping(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Noteneintrag");

        return homescreen;
    }

    private Homescreen getLinks(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Links");

        return homescreen;
    }

    private Homescreen getNextLession(){
        Homescreen homescreen = new Homescreen();
        homescreen.setTitle("Nächste Unterrichtsstunde");

        return homescreen;
    }
}
