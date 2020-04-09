package com.group3.backend.controller;

import com.group3.backend.model.FieldOfStudy;
import com.group3.backend.repository.FieldOfStudyRepository;
import com.group3.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fieldOfStudy")
public class FieldOfStudyController {

    private FieldOfStudyRepository fieldOfStudyRepository;

    @Autowired
    public FieldOfStudyController(FieldOfStudyRepository fieldOfStudyRepository) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

}
