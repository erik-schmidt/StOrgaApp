package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Link;
import com.group3.backend.service.LinkCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@CrossOrigin
public class LinkCollectionController {

    private LinkCollectionService linkCollectionService;
    private AccessChecker accessChecker;

    @Autowired
    public LinkCollectionController(LinkCollectionService linkCollectionService, AccessChecker accessChecker){
        this.linkCollectionService = linkCollectionService;
        this.accessChecker = accessChecker;
    }

    /**
     * ping()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return linkCollectionService.ping();
    }

    /**
     * Get the linkList of a certain Student by its MatrNr.
     * @param matrNr
     * @return
     */
    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getLinkListByStdId(@PathVariable(value = "matrNr")String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNr(matrNr);
    }

    /**
     * Get a specific Link by the MatrNr of the Student and the number of the Link.
     * @param matrNr
     * @param linkNr
     * @return
     */
    @GetMapping("/{matrNr}/get/{linkNr}")
    public ResponseEntity<?> getLinkListByStdIdAndNr(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkNr") int linkNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNrAndNr(matrNr, linkNr);
    }

    /**
     * Add a Link to a Student by using its MatrNr.
     * @param matrNr
     * @param link
     * @return
     */
    @PutMapping("/{matrNr}/add")
    public ResponseEntity<?> addLinkToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.addLinkToStudent(matrNr, link);
    }

    /**
     * Delete a specific Link from a Student by using its MatrNr and the number of the Link.
     * @param matrNr
     * @param linkId
     * @return
     */
    @DeleteMapping("/{matrNr}/delete/{linkId}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "matrNr")String matrNr,@PathVariable(value = "linkId") int linkId, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.deleteLink(matrNr, linkId);
    }

    /**
     * Update a Link by using the MatrNr of the Student, the number of the Link and the new Link-Object which should
     * replace the old one.
     * @param matrNr
     * @param linkId
     * @param link
     * @return
     */
    @PutMapping("/{matrNr}/put/{linkId}")
    public ResponseEntity<?> changeLink(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkId") int linkId, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.changeLink(matrNr, linkId, link);
    }
}
