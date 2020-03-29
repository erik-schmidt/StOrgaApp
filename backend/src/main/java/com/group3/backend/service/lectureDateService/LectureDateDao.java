package com.group3.backend.service.lectureDateService;

import com.group3.backend.model.LectureDate;

import java.util.List;

public interface LectureDateDao {

    List<LectureDate> findAll();
    void insertLectureDate(LectureDate lectureDate);
    void updateLectureDate(LectureDate lectureDate);
    void executeUpdateLectureDate(LectureDate lectureDate);
    void deleteLectureDate(LectureDate lectureDate);
}
