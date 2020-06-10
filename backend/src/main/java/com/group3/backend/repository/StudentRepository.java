package com.group3.backend.repository;

import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The repository for the {@link Student}.
 * Supported methods to find objects are:
 * - By matrNr
 * - By username
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByMatrNr(String matnr);
    Optional<Student> findOneByUsername(String username);
}
