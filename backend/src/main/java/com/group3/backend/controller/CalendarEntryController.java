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
    public ResponseEntity<?> getAllCalendarEntries() {
        return calendarEntryService.getAllCalendarEntries();
    }

    @GetMapping("/get/{matrNr}")
    public ResponseEntity<?> getStudentCalendarEntries(@PathVariable(value = "matrNr") String matrNr, @RequestHeader (name="Authorization") String token) {
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.getStudentCalendarEntries(matrNr);
    }

    @PostMapping("/create/{matrNr}")
    public ResponseEntity<?> createCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.createCalendarEntry(matrNr, calendarEntry);
    }

    @DeleteMapping("/delete/{matrNr}")
    public ResponseEntity<?> deleteCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry, @RequestHeader (name="Authorization") String token){
        if(accessChecker.checkAccess(matrNr, token)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nicht authorisiert für diesen Zugriff. Bitte Einloggen. ");
        }
        return calendarEntryService.deleteCalendarEntry(matrNr, calendarEntry);
    }

    /*@GetMapping("/{matrNr}/get/{description}")
    public CalendarEntry getCalendarEntryByDescription(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "description") String description){
        return calendarEntryService.getCalendarEntryByDescription(matrNr, description);
    }

    @GetMapping("/{matrNr}/get/{date}")
    public List<CalendarEntry> getCalendarEntriesByDate(@PathVariable(value = "matrNr") String matrNr, @PathVariable(value = "date") Date date){
        return calendarEntryService.getCalendarEntryByDate(matrNr, date);
    }

    @PutMapping("/{matrNr}/add")
    public ResponseEntity<CalendarEntry> addEntry(@PathVariable(value = "matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry){
        return calendarEntryService.addCalendarEntryToStudent(matrNr, calendarEntry);
    }*/


    /*@PostMapping("/create")
    public ResponseEntity<CalendarEntry> createCalendarEntry(@RequestBody CalendarEntry calendarEntry){
        return calendarEntryService.createCalendarEntry(calendarEntry);
    }*/

}
