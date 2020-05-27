package com.group3.backend;

import com.group3.backend.model.Link;
import com.group3.backend.model.Student;
import com.group3.backend.service.LinkCollectionService;
import com.group3.backend.service.StudentService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootTest
public class LinkListTests {

    @Autowired
    private StudentService studentService;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private LinkCollectionService linkCollectionService;

    private Random random = new Random();

    @Autowired
    public LinkListTests(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    void addingLinkToStudent(){
        studentService.createStudent(createDummyStudent());
        linkCollectionService.addLinkToStudent(createDummyStudent().getMatrNr(), createDummyLink());
        List<Link> linkListOfStudent = (List<Link>) linkCollectionService.getLinkListByMatrNr(createDummyStudent().getMatrNr()).getBody();
        Assertions.assertTrue(linkListOfStudent.size()==1);
    }

    public Student createDummyStudent(){
        Student student = new Student();
        student.setMatrNr("202481");
        student.setStudentPrename("Liyan");
        student.setStudentFamilyname("Fu-Wacker");
        student.setFieldOfStudy("AIB");
        student.setCurrentSemester(7);
        student.setUsername("LiyanFuW");
        student.setPassword(passwordEncoder.encode("ladsfklajsfl505"));
        return student;
    }

    public Link createDummyLink(){
        Link link = new Link();
        link.setLink(random.nextInt(1000)+"");
        link.setLinkDescription("NewLinkWithTheNumber" + random.nextInt(10000));
        return link;
    }
}
