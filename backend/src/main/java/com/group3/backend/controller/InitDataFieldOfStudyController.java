package com.group3.backend.controller;

import com.group3.backend.model.InitDataFieldOfStudy;
import com.group3.backend.repository.InitDataFieldOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/initDatafieldOfStudy")
public class InitDataFieldOfStudyController {

    private InitDataFieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    public InitDataFieldOfStudyController(InitDataFieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return "reachable";}

    @GetMapping("/get")
    public List<InitDataFieldOfStudy> getAllFieldOfStudy(){
        List<InitDataFieldOfStudy> fieldOfStudyList = fieldOfStudyRepository.findAll();
        return fieldOfStudyList;
    }

    @GetMapping("/get/{id}")
    public InitDataFieldOfStudy getFieldOfStudyByNumber(@PathVariable(value = "id") int id){
        InitDataFieldOfStudy fos = fieldOfStudyRepository.findById(id);
        return fos;
    }

    @PutMapping("/post")
    public ResponseEntity<InitDataFieldOfStudy> createFieldOfStudy(@RequestBody InitDataFieldOfStudy fieldOfStudy){
        InitDataFieldOfStudy fos = new InitDataFieldOfStudy(fieldOfStudy.getDescription(), fieldOfStudy.getName(),
                fieldOfStudy.getSemester(), fieldOfStudy.getCourseList());
        fieldOfStudyRepository.save(fos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public InitDataFieldOfStudy deleteCurriculum(@PathVariable(value = "id") int id){
        InitDataFieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(id);
        fieldOfStudyRepository.delete(fieldOfStudy);
        return fieldOfStudy;
    }

}