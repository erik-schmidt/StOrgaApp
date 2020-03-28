package com.group3.backend.service.curriculumService;

import com.group3.backend.model.Curriculum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CurriculumRowMapper implements RowMapper<Curriculum> {


    @Override
    public Curriculum mapRow(ResultSet resultSet, int i) throws SQLException {
        LocalDate localDate = new LocalDate();
        Curriculum curriculum = new Curriculum();
        curriculum.setDescription(resultSet.getString("curriculumDescription"));
        curriculum.setMilestone(resultSet.getString());
    }
}
