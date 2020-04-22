package com.group3.backend.controller;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get")
    public List<CalendarEntry> getAllCalendarEntries() {
        return calendarEntryService.getAllCalendarEntries();
    }

    @GetMapping("/{matrNr}/get")
    public Set<CalendarEntry> getStudentCalendarEntries(@PathVariable(value = "matrNr") String matrNr) {
        return calendarEntryService.getStudentCalendarEntries(matrNr);
    }

    @GetMapping("/get/{description}")
    public CalendarEntry getCalendarEntryByNumber(@PathVariable(value = "description") String description){
        return calendarEntryService.getCalendarEntryByNumber(description);
    }

    @PutMapping("/{matrNr}/addCalendarEntryToStudent")
    public ResponseEntity<CalendarEntry> addCalendarEntryToStudent(@PathVariable(value = "matrNr") String matrNr, @RequestBody CalendarEntry calendarEntry){
        return calendarEntryService.addCalendarEntryToStudent(matrNr, calendarEntry);
    }

    @PostMapping("/create")
    public ResponseEntity<CalendarEntry> createCalendarEntry(@RequestBody CalendarEntry calendarEntry){
        return calendarEntryService.createCalendarEntry(calendarEntry);
    }

    @DeleteMapping("/delete/{description}")
    public CalendarEntry deleteCalendarEntry(@PathVariable(value = "description") String description){
        return calendarEntryService.deleteCalendarEntry(description);
    }
}
