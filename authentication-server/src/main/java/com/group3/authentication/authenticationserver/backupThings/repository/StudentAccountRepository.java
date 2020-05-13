package com.group3.authentication.authenticationserver.backupThings.repository;

import com.group3.authentication.authenticationserver.backupThings.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//https://gitlab.com/ertantoker/tutorials/spring-boot-security-jwt-example/-/tree/master
@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {
    Optional<StudentAccount> findOneByUsername(String username);
}
