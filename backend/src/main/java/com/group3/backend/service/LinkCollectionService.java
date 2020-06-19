package com.group3.backend.service;

import com.group3.backend.exceptions.CheckMatrNrClass;
import com.group3.backend.exceptions.LinkList.LinkedListWithoutLinkException;
import com.group3.backend.exceptions.LinkList.LinkedListWithoutLinkIDException;
import com.group3.backend.exceptions.NoDescriptionException;
import com.group3.backend.model.Link;
import com.group3.backend.model.Student;
import com.group3.backend.repository.LinkRepository;
import com.group3.backend.repository.StudentRepository;
import com.group3.backend.security.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class LinkCollectionService extends CheckMatrNrClass {

    private LinkRepository linkRepository;
    private StudentRepository studentRepository;
    private Logger logger = LoggerFactory.getLogger(LinkCollectionService.class);
    private PasswordEncoder passwordEncoder;
    private JwtTokenService jwtTokenService;

    @Autowired
    public LinkCollectionService(LinkRepository linkRepository, StudentRepository studentRepository,
                                 PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService){
        this.linkRepository = linkRepository;
        this.studentRepository =studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping(){
        return "reachable";
    }

    /**
     * Is used to get all {@link Link} objects of a specific {@link Student}.
     * Is called by "/{matrNr}/get".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Link} objects of.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          the {@link Link} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getLinkListByMatrNr(String matrNr){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            List<Link> linkList = linkRepository.findAllByStudentMatrNr(matrNr);
            if (linkList.isEmpty()) {
                logger.error("There are no links for this student.");
                return ResponseEntity.status(HttpStatus.OK).body(getEmptyList("Link"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(linkList);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Is used to get a specific {@link Link} of a specific {@link Student}.
     * Is called by "/{matrNr}/get/{linkNr}".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Link} of.
     * @param linkId
     *                  The linkId of the {@link Link} you want to get from the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
                return ResponseEntity.status(HttpStatus.OK).body(getEmptyList("Link"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(link);
        }catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Is used to add a {@link Link} to a {@link Student}.
     * Is called by "/{matrNr}/add".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to add the {@link Link} to.
     * @param link
     *              The {@link Link} you want to add to the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
        }
        catch (Exception e){
            logger.error(e.getClass() + " " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(link);
    }

    /**
     * Is used to delete a {@link Link} from a {@link Student}.
     * Is called by "/{matrNr}/delete/{linkId}".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link Link} from.
     * @param linkId
     *                  The linkId of the {@link Link} you want to delete from the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the deleted
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> deleteLink(String matrNr, int linkId){
        try {
            if (!checkMatriculationNumber(matrNr)){
                throw new Exception("Problem with MatrNr!");
            }
            if (linkId == 0){
                throw new LinkedListWithoutLinkIDException("Error: No linkID given!");
            }
            Link link = linkRepository.findById(linkId);
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
     * Is used to update a specific {@link Link} of a specific {@link Student}. Replaces the old {@link Link} with
     * the new one.
     * Is called by "/{matrNr}/put/{linkId}".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to update the {@link Link} of.
     * @param linkId
     *                  The linkId of the {@link Link} you want to update.
     * @param newLink
     *                  The new {@link Link} which should replace the updated one.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the updated
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
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
            //newLink.setId(link.getId());
            //linkRepository.delete(link);
            link.setLink(newLink.getLink());
            link.setLinkDescription(newLink.getLinkDescription());
            linkRepository.save(link);
            return ResponseEntity.status(HttpStatus.OK).body(newLink);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
    }

    /**
     * Is used to check the syntax of a {@link Link}.
     * @param link
     *              The {@link Link} you want to check.
     * @return
     *          Returns true if the syntax is valid.
     *          Returns false if not.
     */
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

    /**
     * Is used to get all {@link Link} objects from the repository.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link Link} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getAllLinks(){
        try{
            List<Link> linkList = linkRepository.findAll();
            if(linkList.isEmpty()){
                logger.error("Error while reading all Links: There are no links saved");
                return ResponseEntity.status(HttpStatus.OK).body(getEmptyList("Link"));
            }
            logger.info("Links successfully read");
            return ResponseEntity.status(HttpStatus.OK).body(linkList);
        }catch (Exception e){
            logger.error(e.getClass() +" "+e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() +" "+e.getMessage());
        }
    }
}
