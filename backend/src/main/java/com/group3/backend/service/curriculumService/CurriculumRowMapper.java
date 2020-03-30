package com.group3.backend.service.curriculumService;

import com.group3.backend.model.Curriculum;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurriculumRowMapper implements RowMapper<Curriculum> {


    @Override
    public Curriculum mapRow(ResultSet resultSet, int i) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-mm");
        Curriculum curriculum = new Curriculum();
        curriculum.setId(resultSet.getString("curriculumId"));
        curriculum.setDescription(resultSet.getString("curriculumDescription"));
        return curriculum;
    }
}
