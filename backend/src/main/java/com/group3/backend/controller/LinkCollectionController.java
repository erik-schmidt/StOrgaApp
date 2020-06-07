package com.group3.backend.controller;

import com.group3.backend.model.Link;
import com.group3.backend.service.LinkCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.group3.backend.model.Student;

@RestController
@RequestMapping("/link")
@CrossOrigin
public class LinkCollectionController {

    private LinkCollectionService linkCollectionService;
    private AccessChecker accessChecker;

    @Autowired
    public LinkCollectionController(LinkCollectionService linkCollectionService, AccessChecker accessChecker){
        this.linkCollectionService = linkCollectionService;
        this.accessChecker = accessChecker;
    }

    /**
     * The ping-method of this controller. It is used to check if the frontend is able to access the methods of this
     * controller.
     * @return  Returns the String "reachable" if access to the methods is possible.
     */
    @GetMapping("/ping")
    public String ping(){
        return linkCollectionService.ping();
    }

    /**
     * The get-method to get the {@link Link} objects of a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Link} objects for.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          requested {@link Link} objects in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getLinkListByStdId(@PathVariable(value = "matrNr")String matrNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNr(matrNr);
    }

    /**
     * The get-method to get a specific {@link Link} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link Link} for.
     * @param linkNr
     *                  The linkNr of the {@link Link} you want from the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the
     *          requested {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get/{linkNr}")
    public ResponseEntity<?> getLinkListByStdIdAndNr(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkNr") int linkNr, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.getLinkListByMatrNrAndNr(matrNr, linkNr);
    }

    /**
     * The add-method to add a {@link Link} to a {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to add the {@link Link} to.
     * @param link
     *                  The {@link Link} you want to add to the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the added
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @PutMapping("/{matrNr}/add")
    public ResponseEntity<?> addLinkToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.addLinkToStudent(matrNr, link);
    }

    /**
     * The delete-method to delete a specific {@link Link} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link Link} of.
     * @param linkId
     *                  The linkId of the {@link Link} you want to delete from the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the deleted
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @DeleteMapping("/{matrNr}/delete/{linkId}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "matrNr")String matrNr,@PathVariable(value = "linkId") int linkId, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.deleteLink(matrNr, linkId);
    }

    /**
     * The put-method to update an existing {@link Link} from a specific {@link Student}.
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to update the {@link Link} of.
     * @param linkId
     *                  The linkId of the {@link Link} you want to update.
     * @param link
     *                  The new {@link Link} which should replace the old one in the {@link Student}.
     * @param token
     *                  The token to authorize your request.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the updated
     *          {@link Link} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    @PutMapping("/{matrNr}/put/{linkId}")
    public ResponseEntity<?> changeLink(@PathVariable(value = "matrNr")String matrNr, @PathVariable(value = "linkId") int linkId, @RequestBody Link link, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return linkCollectionService.changeLink(matrNr, linkId, link);
    }
}
