package com.group3.backend.controller;

import com.group3.backend.model.Curriculum;
import com.group3.backend.model.FieldOfStudy;
import com.group3.backend.repository.FieldOfStudyRepository;
import com.group3.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fieldOfStudy")
public class FieldOfStudyController {

    private FieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    public FieldOfStudyController(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    @GetMapping("/get")
    public List<FieldOfStudy> getAllFieldOfStudy(){
        List<FieldOfStudy> fieldOfStudyList = fieldOfStudyRepository.findAll();
        return fieldOfStudyList;
    }

    @GetMapping("/get/{id}")
    public FieldOfStudy getFieldOfStudyByNumber(@PathVariable(value = "id") int id){
        FieldOfStudy fos = fieldOfStudyRepository.findById(id);
        return fos;
    }

    @PutMapping("/post")
    public ResponseEntity<FieldOfStudy> createFieldOfStudy(@RequestBody FieldOfStudy fieldOfStudy){
        FieldOfStudy fos = new FieldOfStudy(fieldOfStudy.getDescription(), fieldOfStudy.getName(),
                fieldOfStudy.getSemester(), fieldOfStudy.getCourseIdSet(), fieldOfStudy.getStudent());
        fieldOfStudyRepository.save(fos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public FieldOfStudy deleteCurriculum(@PathVariable(value = "id") int id){
        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findById(id);
        fieldOfStudyRepository.delete(fieldOfStudy);
        return fieldOfStudy;
    }

}
