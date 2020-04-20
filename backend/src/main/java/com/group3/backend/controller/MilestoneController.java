package com.group3.backend.controller;

import com.group3.backend.model.Milestone;
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

    /**
     * reachabilityTest()
     * return a String with a successful message if backend reachable
     * @return String "Test successful"
     */
    @GetMapping("/ping")
    public String ping(){
        return "reachable";}

    @GetMapping("/get")
    public List<Milestone> getAllMilestone(){
        List<Milestone> milestoneList = milestoneRepository.findAll();
        return milestoneList;
    }

    @GetMapping("/get/{id}")
    public Milestone getMilestoneByNumber(@PathVariable(value = "id") int id){
        Milestone ms = milestoneRepository.findById(id);
        return ms;
    }

    @PutMapping("/create")
    public ResponseEntity<Milestone> createMilestone(@RequestBody Milestone milestone){
        Milestone ms = new Milestone(milestone.getText(), milestone.getFinishDate());
        milestoneRepository.save(ms);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public Milestone deleteMilestone(@PathVariable(value = "id") int id){
        Milestone milestone = milestoneRepository.findById(id);
        milestoneRepository.delete(milestone);
        return milestone;
    }
}
