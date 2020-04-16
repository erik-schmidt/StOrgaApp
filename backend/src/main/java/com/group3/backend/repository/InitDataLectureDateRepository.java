package com.group3.backend.repository;

import com.group3.backend.model.InitDataFieldOfStudy;
import com.group3.backend.model.InitDataLectureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InitDataLectureDateRepository extends JpaRepository<InitDataLectureDate, Integer> {
    InitDataLectureDate findById(int id);
}