package com.group3.authentication.authenticationserver.repository;

import com.group3.authentication.authenticationserver.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findOneByUsername(String username);
    Student findByMatrNr(String matnr);

}
