package com.group3.backend.repository;

import com.group3.backend.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, String> {
    List<Link> findAllByStudentMatrNr(String matrNr);
    Link findByStudentMatrNrAndId(String matrNr, int linkId);
    Link findById(int linkId);
}
