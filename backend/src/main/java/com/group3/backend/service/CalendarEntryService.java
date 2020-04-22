package com.group3.backend.service;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CalendarEntryRepository;
import com.group3.backend.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class CalendarEntryService {

    private CalendarEntryRepository calendarEntryRepository;
    private StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(CalendarEntryService.class);

    @Autowired
    public CalendarEntryService(CalendarEntryRepository calendarEntryRepository, StudentRepository studentRepository){
        this.calendarEntryRepository = calendarEntryRepository;
        this.studentRepository = studentRepository;
    }

    public String ping() {
        return "reachable";
    }

    public List<CalendarEntry> getAllCalendarEntries(){
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAll();
        return calendarEntries;
    }

    public Set<CalendarEntry> getStudentCalendarEntries (String matrNr) {
        Student student = studentRepository.findByMatrNr(matrNr);
        return student.getCalendarEntries();
    }

    public CalendarEntry getCalendarEntryByNumber(String description){
        CalendarEntry calendarEntry = calendarEntryRepository.findByDescription(description);
        return calendarEntry;
    }

    public ResponseEntity<CalendarEntry> addCalendarEntryToStudent(String matrNr, CalendarEntry calendarEntry){
        try {
            Student student = studentRepository.findByMatrNr(matrNr);
            CalendarEntry calendarEntry1 = calendarEntryRepository.findByDescription(calendarEntry.getDescription());
            calendarEntry1.setStudent(student);
            calendarEntryRepository.save(calendarEntry1);
            calendarEntryRepository.saveAndFlush(calendarEntry1);
        } catch (Exception e) {
            logger.error(e.getClass() + " " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<CalendarEntry> createCalendarEntry(CalendarEntry calendarEntry) {
        CalendarEntry calendarEntry1 = new CalendarEntry(calendarEntry.getName(), calendarEntry.getDateOfEntry(), calendarEntry.getDescription());
        calendarEntryRepository.save(calendarEntry1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public CalendarEntry deleteCalendarEntry(String description){
        CalendarEntry calendarEntry = calendarEntryRepository.findByDescription(description);
        calendarEntryRepository.delete(calendarEntry);
        return calendarEntry;
    }


}
