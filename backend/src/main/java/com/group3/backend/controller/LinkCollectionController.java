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

    @GetMapping("/get/{matrNr}")
    public List<Link> getLinkListByStdId(@PathVariable(value = "matrNr")String matrNr){
        return linkCollectionService.getLinkListByMatrNr(matrNr);
    }

    @GetMapping("/get/{matrNr}/{linkNr}")
    public Link getLinkListByStdIdAndNr(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkNr") long linkNr){
        return linkCollectionService.getLinkListByMatrNrAndNr(matrNr, linkNr);
    }

    @PutMapping("/add/{matrNr}")
    public ResponseEntity<Link> addLinkToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Link link){
        return linkCollectionService.addLinkToStudent(matrNr, link);
    }

    @DeleteMapping("/delete/{matrNr}/{linkId}")
    public Link deleteLink(@PathVariable(value = "matrNr")String matrNr,@PathVariable(value = "linkId") long linkId){
        return linkCollectionService.deleteLink(matrNr, linkId);
    }
}
