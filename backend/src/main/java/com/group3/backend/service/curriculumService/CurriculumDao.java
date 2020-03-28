package com.group3.backend.service.curriculumService;

import com.group3.backend.model.Curriculum;

import java.util.List;

public interface CurriculumDao {

    List<Curriculum> findAll();
    void insertCurriculum(Curriculum curriculum);
    void updateCurriculum(Curriculum curriculum);
    void executeUpdateCurriculum(Curriculum curriculum);
    void deletCurriculum(Curriculum curriculum);
}
