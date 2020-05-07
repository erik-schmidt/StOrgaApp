package com.group3.backend.controller;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CalendarEntryController(CalendarEntryService calendarEntryService) {
        this.calendarEntryService = calendarEntryService;
    }

    @GetMapping("/ping")
    public String ping() {
        return calendarEntryService.ping();
    }

    @GetMapping("/getAll")
    public List<CalendarEntry> getAllCalendarEntries() {
        return calendarEntryService.getAllCalendarEntries();
    }

    @GetMapping("/get/{matrNr}")
    public List<CalendarEntry> getStudentCalendarEntries(@PathVariable(value = "matrNr") String matrNr) {
        return calendarEntryService.getStudentCalendarEntries(matrNr);
    }

    @PostMapping("/{matrNr}/create")
    public ResponseEntity<CalendarEntry> createCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry){
        return calendarEntryService.createCalendarEntry(matrNr, calendarEntry);
    }

    @DeleteMapping("/delete/{matrNr}")
    public ResponseEntity<CalendarEntry> deleteCalendarEntry(@PathVariable(value ="matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry){
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
