package com.group3.backend.repository;

import com.group3.backend.model.LectureDate;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureDateRepository {
    LectureDate findById(int id);
}
