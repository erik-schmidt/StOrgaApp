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
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Random;

@SpringBootTest
@Transactional
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

    @BeforeEach
    void deleteLinks(){
        if(linkCollectionService.getAllLinks().getStatusCode().equals(HttpStatus.OK)){
            List<Link> linkList = (List<Link>)linkCollectionService.getAllLinks().getBody();
            for(Link link : linkList){
                linkCollectionService.deleteLink(link.getStudent().getMatrNr(), link.getId());
                System.out.println((List<Link>)linkCollectionService.getAllLinks().getBody());
            }
        }
    }

    @Test
    void addingLinkToStudent(){
        if(linkCollectionService.getAllLinks().getStatusCode().equals(HttpStatus.OK)){
            List<Link> linkList = (List<Link>)linkCollectionService.getAllLinks().getBody();
            for(Link link : linkList){
                linkCollectionService.deleteLink(link.getStudent().getMatrNr(), link.getId());
                System.out.println((List<Link>)linkCollectionService.getAllLinks().getBody());
            }
        }
        studentService.createStudent(createDummyStudent());
        linkCollectionService.addLinkToStudent(createDummyStudent().getMatrNr(), createDummyLink());
        List<Link> linkListOfStudent = (List<Link>)linkCollectionService.getAllLinks().getBody();
        System.out.println(linkListOfStudent);
        Assertions.assertTrue(linkListOfStudent.size()==1);
        for(int i = 0; i<11; i++){
            linkCollectionService.addLinkToStudent(createDummyStudent().getMatrNr(), createDummyLink());
        }
        Assertions.assertTrue(linkListOfStudent.size()==11);
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
