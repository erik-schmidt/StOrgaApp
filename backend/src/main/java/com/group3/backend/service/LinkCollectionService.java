package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.LinkList.LinkedListWithoutLinkException;
import com.group3.backend.exceptions.LinkList.LinkedListWithoutLinkIDException;
import com.group3.backend.exceptions.NoDescriptionException;
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
public class LinkCollectionService extends CheckMatrNrClass {

    private LinkRepository linkRepository;
    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(LinkCollectionService.class);

    @Autowired
    public LinkCollectionService(LinkRepository linkRepository, StudentRepository studentRepository){
        this.linkRepository = linkRepository;
        this.studentRepository =studentRepository;
    }

    /**
     * ping-method -> *ping, ping*
     * @return
     */
    public String ping(){
        return "reachable";
    }

    /**
     * Returns the LinkList for the Student with the given MatrNr.
     * @param matrNr
     * @return
     */
    public ResponseEntity<?> getLinkListByMatrNr(String matrNr){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            List<Link> linkList = linkRepository.findAllByStudentMatrNr(matrNr);
            if (linkList.isEmpty()) {
                logger.error("There are no links for this student.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: No saved links");
            }
            return ResponseEntity.status(HttpStatus.OK).body(linkList);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Returns the Link from the Student with the given MatrNr and the given number.
     * @param matrNr
     * @param linkId
     * @return
     */
    public ResponseEntity<?> getLinkListByMatrNrAndNr(String matrNr, int linkId){
        try{
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            if (linkId == 0){
                throw new LinkedListWithoutLinkIDException("Error: No linkID given!");
            }
            Link link = linkRepository.findByStudentMatrNrAndId(matrNr, linkId);
            if (link == null){
                logger.error("There are no link for this student with that linkId");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: No saved link");
            }
            return ResponseEntity.status(HttpStatus.OK).body(link);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Adds the given Link to the Student with the given MatrNr.
     * @param matrNr
     * @param link
     * @return
     */
    public ResponseEntity<?> addLinkToStudent(String matrNr, Link link){
        try{
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            if (!checkLink(link)){
                throw new Exception("Problem with the given link object!");
            }
            link.setStudent(studentRepository.findByMatrNr(matrNr));
            linkRepository.save(link);
            linkRepository.saveAndFlush(link);
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(link);
    }

    /**
     * Deletes the Link with the given linkId if it matches with the Student of the given MatrNr.
     * @param matrNr
     * @param linkId
     * @return
     */
    public ResponseEntity<?> deleteLink(String matrNr, int linkId){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            if (linkId == 0){
                throw new LinkedListWithoutLinkIDException("Error: No linkID given!");
            }
            Link link = linkRepository.findByStudentMatrNrAndId(matrNr, linkId);
            if (link == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no link for that student with this number");
            }
            try{
                linkRepository.delete(link);
                return ResponseEntity.status(HttpStatus.OK).body(link);
            }
            catch (Exception e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Method to change a Link by using the MatrNr of the Student and the linkId and replacing that Link with the given
     * one.
     * @param matrNr
     * @param linkId
     * @param newLink
     * @return
     */
    public ResponseEntity<?> changeLink(String matrNr, int linkId, Link newLink){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            if (linkId == 0){
                throw new LinkedListWithoutLinkIDException("Error: No linkID given!");
            }
            if (!checkLink(newLink)){
                throw new Exception("Problem with the given link object!");
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        Link link = linkRepository.findByStudentMatrNrAndId(matrNr, linkId);
        if (link == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: There is no link for that student with this number");
        }
        try{
            newLink.setId(link.getId());
            linkRepository.delete(link);
            linkRepository.save(newLink);
            return ResponseEntity.status(HttpStatus.OK).body(newLink);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    public boolean checkLink(Link link){
        try {
            if (link.getLink().trim().isEmpty()){
                throw new LinkedListWithoutLinkException("Error: Link object has no link!");
            }
            if (link.getLinkDescription().trim().isEmpty()){
                throw new NoDescriptionException("Error: Link has no description!");
            }
            return true;
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return false;
        }
    }

}
