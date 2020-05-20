package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Link;
import com.group3.backend.service.LinkCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@CrossOrigin
public class LinkCollectionController {

    private LinkCollectionService linkCollectionService;

    @Autowired
    public LinkCollectionController(LinkCollectionService linkCollectionService){
        this.linkCollectionService = linkCollectionService;
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
    @GetMapping("/get/{matrNr}")
    public ResponseEntity<?> getLinkListByStdId(@PathVariable(value = "matrNr")String matrNr){
        return linkCollectionService.getLinkListByMatrNr(matrNr);
    }

    /**
     * Get a specific Link by the MatrNr of the Student and the number of the Link.
     * @param matrNr
     * @param linkNr
     * @return
     */
    @GetMapping("/get/{matrNr}/{linkNr}")
    public ResponseEntity<?> getLinkListByStdIdAndNr(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkNr") int linkNr){
        return linkCollectionService.getLinkListByMatrNrAndNr(matrNr, linkNr);
    }

    /**
     * Add a Link to a Student by using its MatrNr.
     * @param matrNr
     * @param link
     * @return
     */
    @PutMapping("/add/{matrNr}")
    public ResponseEntity<?> addLinkToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Link link){
        return linkCollectionService.addLinkToStudent(matrNr, link);
    }

    /**
     * Delete a specific Link from a Student by using its MatrNr and the number of the Link.
     * @param matrNr
     * @param linkId
     * @return
     */
    @DeleteMapping("/delete/{matrNr}/{linkId}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "matrNr")String matrNr,@PathVariable(value = "linkId") int linkId){
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
    @PutMapping("/put/{matrNr}/{linkId}")
    public ResponseEntity<?> changeLink(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkId") int linkId, @RequestBody Link link){
        return linkCollectionService.changeLink(matrNr, linkId, link);
    }
}
