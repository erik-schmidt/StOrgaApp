package com.group3.backend.controller;

import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.CurriculumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/curriculum")
public class CurriculumController {

    private com.group3.backend.repository.CurriculumRepository curriculumRepository;

    @Autowired
    public CurriculumController(CurriculumRepository curriculumRepository){
        this.curriculumRepository = curriculumRepository;
    }
}
