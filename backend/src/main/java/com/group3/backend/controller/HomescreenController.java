package com.group3.backend.controller;

import com.group3.backend.service.HomescreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/homescreen")
@CrossOrigin()
public class HomescreenController {

    private AccessChecker accessChecker;
    private HomescreenService homescreenService;

    @Autowired
    public HomescreenController(AccessChecker accessChecker, HomescreenService homescreenService){
        this.accessChecker = accessChecker;
        this.homescreenService = homescreenService;
    }

    /**
     * The ping-method of this controller. It is used to check if the frontend is able to access the methods of this
     * controller.
     * @return  Returns the String "reachable" if access to the methods is possible.
     */
    @GetMapping("/ping")
    public String ping(){return homescreenService.ping();}

    @GetMapping("/getHomescreen/{matrNr}")
    public ResponseEntity getHomescreenItems(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return homescreenService.getHomescreenItems(matrNr);
    }
}
