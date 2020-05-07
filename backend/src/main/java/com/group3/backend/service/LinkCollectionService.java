package com.group3.backend.service;

import com.group3.backend.model.Link;
import com.group3.backend.repository.LinkRepository;
import com.group3.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkCollectionService {

    private LinkRepository linkRepository;
    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(LinkCollectionService.class);

    @Autowired
    public LinkCollectionService(LinkRepository linkRepository, StudentRepository studentRepository){
        this.linkRepository = linkRepository;
        this.studentRepository =studentRepository;
    }

    public String ping(){
        return "reachable";
    }

    public List<Link> getLinkListByMatrNr(String matrNr){
        List<Link> linkList = linkRepository.findAllByStudentMatrNr(matrNr);
        return linkList;
    }

    public Link getLinkListByMatrNrAndNr(String matrNr, long linkId){
        Link link = linkRepository.findByStudentMatrNrAndLinkId(matrNr, linkId);
        return link;
    }

    public ResponseEntity<Link> addLinkToStudent(String matrNr, Link link){
        try{
            link.setStudent(studentRepository.findByMatrNr(matrNr));
            linkRepository.save(link);
            linkRepository.saveAndFlush(link);
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Link deleteLink(String matrNr, long linkId){
        Link link = linkRepository.findByStudentMatrNrAndLinkId(matrNr, linkId);
        linkRepository.delete(link);
        return link;
    }


}
