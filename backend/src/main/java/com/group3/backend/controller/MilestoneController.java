package com.group3.backend.controller;

import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.MilestoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/milestone")
public class MilestoneController {

    private MilestoneRepository milestoneRepository;

    @Autowired
    public MilestoneController(MilestoneRepository milestoneRepository){
        this.milestoneRepository = milestoneRepository;
    }
}
