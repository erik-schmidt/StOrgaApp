package com.group3.backend.repository;

import com.group3.backend.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The repository for the {@link Link}.
 * Supported methods to find objects are:
 * - By studentMatrNr
 * - By studentMatrNrAndId
 */

public interface LinkRepository extends JpaRepository<Link, String> {
    List<Link> findAllByStudentMatrNr(String matrNr);
    Link findByStudentMatrNrAndId(String matrNr, int linkId);
    Link findById(int linkId);
    @Query("select max(id) from Link")
    int findMaxID();
}
