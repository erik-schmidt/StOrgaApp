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

import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
public class CalendarEntryService {

    private CalendarEntryRepository calendarEntryRepository;
    private StudentService studentService;
    private StudentRepository studentRepository;

    private Logger logger = LoggerFactory.getLogger(CalendarEntryService.class);

    @Autowired
    public CalendarEntryService(CalendarEntryRepository calendarEntryRepository, StudentService studentService, StudentRepository studentRepository){
        this.calendarEntryRepository = calendarEntryRepository;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    public String ping() {
        return "reachable";
    }

    public List<CalendarEntry> getAllCalendarEntries(){
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAll();
        return calendarEntries;
    }

    public List<CalendarEntry> getStudentCalendarEntries (String matrNr) {
        Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAllByStudentId(student.getId());
        return calendarEntries;
    }

    public ResponseEntity<CalendarEntry> createCalendarEntry(String matrNr, CalendarEntry calendarEntry) {
        Student student = (Student) studentService.getStudentByNumber(matrNr).getBody();
        calendarEntry.setStudent(student);
        Set<CalendarEntry> calendarEntries = student.getCalendarEntries();
        calendarEntries.add(calendarEntry);
        student.setCalendarEntries(calendarEntries);
        studentRepository.save(student);
        //CalendarEntry calendarEntry1 = new CalendarEntry(calendarEntry.getName(), calendarEntry.getEntryStartTime(), calendarEntry.getEntryFinishTime(), calendarEntry.getEntryDate(), calendarEntry.getDescription());
        //calendarEntryRepository.save(calendarEntry1);
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntry);
    }

    public ResponseEntity<CalendarEntry> deleteCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        Student student = (Student) studentService.getStudentByNumber(matrNr).getBody();
        Set<CalendarEntry> calendarEntries = student.getCalendarEntries();
        CalendarEntry calendarEntryDelete = null;
        for(CalendarEntry calendarEntry1 : calendarEntries){
            if(calendarEntry.getDescription().equals(calendarEntry1.getDescription())&&calendarEntry.getName().equals(calendarEntry1.getName())){
                calendarEntryDelete = calendarEntry1;
                calendarEntries.remove(calendarEntry1);
                student.setCalendarEntries(calendarEntries);
            }
        }
        //calendarEntryRepository.deleteById(calendarEntryDelete.getId());
        calendarEntryRepository.delete(calendarEntryDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   /* public CalendarEntry getCalendarEntryByDescription(String matrNr, String description){
        CalendarEntry calendarEntry = calendarEntryRepository.findByDescription(matrNr, description);
        return calendarEntry;
    }

    public List<CalendarEntry> getCalendarEntryByDate(String matrNr, Date entryDate){
        List<CalendarEntry> calendarEntriesByDate = calendarEntryRepository.findByDate(matrNr, entryDate);
        return calendarEntriesByDate;
    }

    // TODO: 24.04.2020 Monthly CalendarEntries
    public List<CalendarEntry> getCalendarEntryByMonth(String matrNr, Date entryDate){
        return null;
    }

    public ResponseEntity<CalendarEntry> addCalendarEntryToStudent(String matrNr, CalendarEntry calendarEntry){
        try {
            Student student = studentRepository.findByMatrNr(matrNr);
            CalendarEntry calendarEntry1 = calendarEntryRepository.findByDescription(matrNr, calendarEntry.getDescription());
            calendarEntry1.setStudent(student);
            calendarEntryRepository.save(calendarEntry1);
            calendarEntryRepository.saveAndFlush(calendarEntry1);
        } catch (Exception e) {
            logger.error(e.getClass() + " " + e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }*/




}
