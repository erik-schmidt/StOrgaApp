package com.group3.backend.controller;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/ping")
    public String ping() {
        return calendarEntryService.ping();
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllCalendarEntries(@RequestHeader (name="Authorization") String token) {
        if(accessChecker.checkAdmin(token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not autorized for this request");
        }
        return calendarEntryService.getAllCalendarEntries();
    }


    @GetMapping("/{matrNr}/get")
    public ResponseEntity<?> getCalendarEntriesByStudent_Id(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token) {
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.getCalendarEntriesByStudent_Id(matrNr);
    }

    @PostMapping("/{matrNr}/create")
    public ResponseEntity<?> createCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.createCalendarEntry(matrNr, calendarEntry);
    }

    @DeleteMapping("/{matrNr}/delete")
    public ResponseEntity<?> deleteCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.deleteCalendarEntry(matrNr, calendarEntry);
    }

}
