package com.group3.backend.service.curriculumService;

import com.group3.backend.model.Curriculum;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class CurriculumDaoImpl implements CurriculumDao {

    NamedParameterJdbcTemplate template;

    public CurriculumDaoImpl (NamedParameterJdbcTemplate template){
        this.template = template;
    }

    @Override
    public List<Curriculum> findAll() {
        return template.query("select * from curriculum", new CurriculumRowMapper);
    }

    @Override
    public void insertCurriculum(Curriculum curriculum) {

    }

    @Override
    public void updateCurriculum(Curriculum curriculum) {

    }

    @Override
    public void executeUpdateCurriculum(Curriculum curriculum) {

    }

    @Override
    public void deletCurriculum(Curriculum curriculum) {

    }
}
