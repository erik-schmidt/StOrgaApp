package com.group3.backend.service.lectureDateService;

import com.group3.backend.model.LectureDate;

import java.util.List;

public interface LectureDateDao {

    List<LectureDate> findAll();
    void insertCourse(LectureDate lectureDate);
    void updateCourse(LectureDate lectureDate);
    void executeUpdateCourse(LectureDate lectureDate);
    void deleteCourse(LectureDate lectureDate);
}
