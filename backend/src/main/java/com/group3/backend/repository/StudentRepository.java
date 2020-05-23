package com.group3.backend.repository;

import com.group3.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByMatrNr(String matnr);
    Optional<Student> findOneByUsername(String username);
}
