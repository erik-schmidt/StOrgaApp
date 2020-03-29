package com.group3.backend.service.fieldOfStudyService;


import com.group3.backend.model.FieldOfStudy;

import java.util.List;

public interface FieldOfStudyDao {
    List<FieldOfStudy> findAll();
    void insertFieldOfStudy(FieldOfStudy fieldOfStudy);
    void updateFieldOfStudy(FieldOfStudy fieldOfStudy);
    void executeUpdateFieldOfStudy(FieldOfStudy fieldOfStudy);
    void deleteFieldOfStudy(FieldOfStudy fieldOfStudy);

}
