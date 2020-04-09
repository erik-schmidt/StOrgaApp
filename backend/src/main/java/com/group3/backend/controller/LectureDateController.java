package com.group3.backend.controller;

import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.LectureDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lectureDate")
public class LectureDateController {

    private LectureDateRepository lectureDateRepository;

    @Autowired
    public LectureDateController(LectureDateRepository lectureDateRepository){
        this.lectureDateRepository = lectureDateRepository;
    }
}
