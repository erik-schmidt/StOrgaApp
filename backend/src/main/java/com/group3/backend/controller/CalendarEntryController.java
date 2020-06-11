package com.group3.backend.controller;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import com.group3.backend.model.Student;

@RestController
@RequestMapping("/calendarEntry")
@CrossOrigin
public class CalendarEntryController {

    private CalendarEntryService calendarEntryService;
    private AccessChecker accessChecker;

    @Autowired
    public CalendarEntryController(CalendarEntryService calendarEntryService, AccessChecker accessChecker) {
        this.calendarEntryService = calendarEntryService;
        this.accessChecker = accessChecker;
    }

    /**
     * The ping-method of this controller. It is used to check if the frontend is
     * able to access the methods of this controller.
     *
     * @return Returns the String "reachable" if access to the methods is possible.
     */
    @GetMapping("/ping")
    public String ping() {
        return calendarEntryService.ping();
    }

    /**
     * The get-method to get all {@link CalendarEntry} objects from the repository.
     *
     * @param token The token to authorize your request.
     * @return Returns a ResponseEntity. If the request was successful, the
     *         HTTPStatus is 'OK' and you get a list of {@link CalendarEntry}
     *         objects in its body. If the request wasn't successful you get a
     *         HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCalendarEntries(@RequestHeader(name = "Authorization") String token) {
        if (accessChecker.checkAdmin(token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return calendarEntryService.getAllCalendarEntries();
    }

    /**
     * The get-method to get all {@link CalendarEntry} of a specific
     * {@link Student}.
     *
     * @param matrNr The matrNr of the {@link Student} you want the
     *               {@link CalendarEntry} of.
     * @param token  The token to authorize your request for the specific
     *               {@link Student}.
     * @return Returns a ResponseEntity. If the request was successful, the
     *         HTTPStatus is 'OK' and you get a list of {@link CalendarEntry}
     *         objects in its body. If the request wasn't successful you get a
     *         HTTPStatus 'BAD-REQUEST'.
     */
    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getCalendarEntriesByStudent_Id(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token) {
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.getCalendarEntriesByStudent_Id(matrNr);
    }

    @GetMapping("/{matrNr}/getWeek")
    public ResponseEntity<?> getCalendarEntriesByStudent_IdAndEntryDateAndEntryDate(
            @PathVariable(value = "matrNr") String matrNr,
            @RequestBody LocalDate dateStart,
            @RequestBody LocalDate dateEnd,
            @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.getCalendarEntriesByStudent_IdAndEntryDateAndEntryDate(matrNr, dateStart, dateEnd);

    }

    /**
     * The create-method to create a new {@link CalendarEntry} for a specific
     * {@link Student}.
     *
     * @param matrNr        The matrNr of the {@link Student} you want to create the
     *                      {@link CalendarEntry} for.
     * @param calendarEntry The {@link CalendarEntry} object you want to create for
     *                      the {@link Student}.
     * @param token         The token to authorize your request for the specific
     *                      {@link Student}.
     * @return Returns a ResponseEntity. If the request was successful, the
     *         HTTPStatus is 'OK' and you get the created {@link CalendarEntry} in
     *         its body. If the request wasn't successful you get a HTTPStatus
     *         'BAD-REQUEST'.
     */
    @PostMapping("/{matrNr}/create")
    public ResponseEntity<?> createCalendarEntry(@PathVariable(value = "matrNr") String matrNr,
            @RequestBody CalendarEntry calendarEntry, @RequestHeader(name = "Authorization") String token) {
        if (accessChecker.checkAccess(matrNr, token)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.createCalendarEntry(matrNr, calendarEntry);
    }

    @DeleteMapping("/{matrNr}/delete/{id}")
    public ResponseEntity<?> deleteCalendarEntryFromStudent(@PathVariable(value ="matrNr") String matrNr, @PathVariable(value = "id") int id, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.deleteCalendarEntryFromStudent(matrNr, id);
    }
}
