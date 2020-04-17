package com.group3.backend.controller;

import com.group3.backend.model.Course;
import com.group3.backend.model.Curriculum;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    private com.group3.backend.repository.CurriculumRepository curriculumRepository;

    @Autowired
    public CurriculumController(CurriculumRepository curriculumRepository){
        this.curriculumRepository = curriculumRepository;
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
    public List<Curriculum> getAllCurriculum(){
        List<Curriculum> curriculumList = curriculumRepository.findAll();
        return curriculumList;
    }

    @GetMapping("/get/{id}")
    public Curriculum getCurriculumByNumber(@PathVariable(value = "id") int id){
        Curriculum cur = curriculumRepository.findById(id);
        return cur;
    }

    @PutMapping("/post")
    public ResponseEntity<Curriculum> createCurriculum(@RequestBody Curriculum curriculum){
        Curriculum cur = new Curriculum(curriculum.getDescription(), curriculum.getNotesSet(),
                curriculum.getMilestoneList(), curriculum.getStudent());
        curriculumRepository.save(cur);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Curriculum deleteCurriculum(@PathVariable(value = "id") int id){
        Curriculum curriculum = curriculumRepository.findById(id);
        curriculumRepository.delete(curriculum);
        return curriculum;
    }
}
