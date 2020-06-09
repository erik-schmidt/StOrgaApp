package com.group3.backend;

import com.group3.backend.model.Link;
import com.group3.backend.service.LinkCollectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class LinkListTests {

    @Autowired
    private LinkCollectionService linkCollectionService;
//    private Random random = new Random();
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public LinkListTests(PasswordEncoder passwordEncoder){
//        this.passwordEncoder = passwordEncoder;
//    }

    @Test
    void testLinkList(){
        if (linkCollectionService.getAllLinks().getBody() == null){
            System.out.println("No links saved.");
        }
        else{
            List<Link> linkList = (List<Link>) linkCollectionService.getAllLinks().getBody();
            System.out.println("Gespeicherte Links:");
            for(Link l : linkList){
                System.out.println(l);
            }
        }
        System.out.println("Füge Link hinzu");
        linkCollectionService.addLinkToStudent("222222", createRandomLink());
        List<Link> linkList = (List<Link>) linkCollectionService.getAllLinks().getBody();
        System.out.println("Gespeicherte Links:");
        for(Link l : linkList){
            System.out.println(l);
        }
        System.out.println("Lösche Links");
        for (Link l : linkList){
            linkCollectionService.deleteLink("222222", l.getId());
        }
        linkList = (List<Link>) linkCollectionService.getAllLinks().getBody();
        System.out.println("Gespeicherte Links:");
        for(Link l : linkList){
            System.out.println(l);
        }
    }

    public Link createRandomLink(){
        Link link = new Link();
        link.setLinkDescription("DummyLink");
        link.setLink("https://dummyLink");
        return link;
    }
}
