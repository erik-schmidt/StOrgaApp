package com.group3.backend.controller;

import com.group3.backend.model.FieldOfStudy;
import com.group3.backend.model.LectureDate;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.LectureDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectureDate")
public class LectureDateController {

    private LectureDateRepository lectureDateRepository;

    @Autowired
    public LectureDateController(LectureDateRepository lectureDateRepository){
        this.lectureDateRepository = lectureDateRepository;
    }

    @GetMapping("/get")
    public List<LectureDate> getAllLectureDates(){
        List<LectureDate> lectureDateList = lectureDateRepository.findAll();
        return lectureDateList;
    }

    @GetMapping("/get/{id}")
    public LectureDate getLectureDateByNumber(@PathVariable(value = "id") int id){
        LectureDate ld = lectureDateRepository.findById(id);
        return ld;
    }

    @PutMapping("/post")
    public ResponseEntity<LectureDate> createLectureDate(@RequestBody LectureDate lectureDate){
        LectureDate ld = new LectureDate(lectureDate.getWeekday(), lectureDate.getStartTime(),
                lectureDate.getFinishTime(), lectureDate.getCourse());
        lectureDateRepository.save(ld);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public LectureDate deleteLectureDate(@PathVariable(value = "id") int id){
        LectureDate lectureDate = lectureDateRepository.findById(id);
        lectureDateRepository.delete(lectureDate);
        return lectureDate;
    }
}
