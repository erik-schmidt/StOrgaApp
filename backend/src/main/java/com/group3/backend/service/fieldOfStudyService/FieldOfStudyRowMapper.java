package com.group3.backend.service.fieldOfStudyService;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.group3.backend.model.FieldOfStudy;
import org.springframework.jdbc.core.RowMapper;

public class FieldOfStudyRowMapper implements RowMapper<FieldOfStudy> {

    @Override

    public FieldOfStudy mapRow(ResultSet rs, int arg1) throws SQLException {
        FieldOfStudy fieldOfStudy = new FieldOfStudy();
        fieldOfStudy.setId(Integer.parseInt(rs.getString("fieldOfStudyId")));
        fieldOfStudy.setDescription(rs.getString("fieldOfStudyDescription"));
        fieldOfStudy.setName(rs.getString("fieldOfStudyName"));
        return fieldOfStudy;
    }

}