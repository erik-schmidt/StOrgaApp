package com.group3.backend.controller;

import com.group3.backend.model.InitDataLectureDate;
import com.group3.backend.repository.InitDataLectureDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectureDate")
public class InitDataLectureDateController {

    private InitDataLectureDateRepository lectureDateRepository;

    @Autowired
    public InitDataLectureDateController(InitDataLectureDateRepository lectureDateRepository){
        this.lectureDateRepository = lectureDateRepository;
    }

    @GetMapping("/get")
    public List<InitDataLectureDate> getAllLectureDates(){
        List<InitDataLectureDate> lectureDateList = lectureDateRepository.findAll();
        return lectureDateList;
    }

    @GetMapping("/get/{id}")
    public InitDataLectureDate getLectureDateByNumber(@PathVariable(value = "id") int id){
        InitDataLectureDate ld = lectureDateRepository.findById(id);
        return ld;
    }

    @PutMapping("/post")
    public ResponseEntity<InitDataLectureDate> createLectureDate(@RequestBody InitDataLectureDate lectureDate){
        InitDataLectureDate ld = new InitDataLectureDate(lectureDate.getWeekday(), lectureDate.getStartTime(),
                lectureDate.getFinishTime(), lectureDate.getCourse());
        lectureDateRepository.save(ld);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public InitDataLectureDate deleteLectureDate(@PathVariable(value = "id") int id){
        InitDataLectureDate lectureDate = lectureDateRepository.findById(id);
        lectureDateRepository.delete(lectureDate);
        return lectureDate;
    }
}