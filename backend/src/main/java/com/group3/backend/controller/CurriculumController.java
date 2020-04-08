package com.group3.backend.controller;

import com.group3.backend.model.Curriculum;
import com.group3.backend.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
@CrossOrigin()
public class CurriculumController {

    private CurriculumRepository curriculumRepository;

    @Autowired
    public CurriculumController (CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @GetMapping("/get")
    public List<Curriculum> getAllCurricula() {
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        return curriculumList;
    }

    @PutMapping("/create")
    public ResponseEntity<Curriculum> createCurriculum(@RequestBody Curriculum curriculum) {
        Curriculum curriculum1 = new Curriculum(curriculum.getDescription());
        curriculumRepository.save(curriculum1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // TODO: 08.04.2020 Warum bekommt er hier durch findById nur ein Repo zurück und kein Curriculum Objekt?
    @DeleteMapping("/delete")
    public Curriculum deleteCurriculum(@PathVariable(value = "id") int id) {
        /*
        Curriculum curriculum = curriculumRepository.findById(id);
        curriculumRepository.delete(curriculum);
        return curriculum;
         */
        return null;
    }

}
