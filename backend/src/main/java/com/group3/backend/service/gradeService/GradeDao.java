package com.group3.backend.service.gradeService;

import com.group3.backend.model.Grade;

import java.util.List;

public interface GradeDao {

    List<Grade> findAll();
    void insertGrade(Grade grade);
    void updateGrade(Grade grade);
    void executeUpdateGrade(Grade grade);
    void deleteGrade(Grade grade);
}
