package com.group3.backend.controller;

import com.group3.backend.model.LectureDate;
import com.group3.backend.model.Milestone;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/milestone")
public class MilestoneController {

    private MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneController(MilestoneRepository milestoneRepository){
        this.milestoneRepository = milestoneRepository;
    }

    @GetMapping("/get")
    public List<Milestone> getAllMilestone(){
        List<Milestone> milestoneList = milestoneRepository.findAll();
        return milestoneList;
    }

    @PutMapping("/post")
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone){
        Milestone ms = new Milestone(milestone.getText(), milestone.getFinishDate(), milestone.getCurriculum());
        milestoneRepository.save(ms);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Milestone deleteMilestone(@PathVariable(value = "id") int id){
        Milestone milestone = milestoneRepository.findById(id);
        milestoneRepository.delete(milestone);
        return milestone;
    }
}
