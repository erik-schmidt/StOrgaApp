package com.group3.backend.service;

import com.group3.backend.exceptions.*;
import com.group3.backend.exceptions.NoDescriptionException;
import com.group3.backend.exceptions.Calendar.CalendarWithoutFinishTimeException;
import com.group3.backend.exceptions.Calendar.CalendarWithoutNameException;
import com.group3.backend.exceptions.Calendar.CalendarWithoutStartTimeException;
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

import java.util.List;
import java.util.Set;

@Service
public class CalendarEntryService extends CheckMatrNrClass{

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

    public ResponseEntity<?> getAllCalendarEntries(){
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntries);
    }

    public ResponseEntity<?> getStudentCalendarEntries (String matrNr) {
        Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAllByStudentId(student.getId());
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntries);
    }

    public ResponseEntity<CalendarEntry> createCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        try {
            if (!checkCalendarObject(calendarEntry)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
            }
            if (!checkMatriculationNumber(matrNr)){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
            }
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
        catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
    }

    public ResponseEntity<CalendarEntry> deleteCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        try {
            if (!checkCalendarObject(calendarEntry)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
            }
            if (!checkMatriculationNumber(matrNr)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
            }
            Student student = (Student) studentService.getStudentByNumber(matrNr).getBody();
            Set<CalendarEntry> calendarEntries = student.getCalendarEntries();
            CalendarEntry calendarEntryDelete = null;
            for (CalendarEntry calendarEntry1 : calendarEntries) {
                if (calendarEntry.getDescription().equals(calendarEntry1.getDescription()) && calendarEntry.getName().equals(calendarEntry1.getName())) {
                    calendarEntryDelete = calendarEntry1;
                    calendarEntries.remove(calendarEntry1);
                    student.setCalendarEntries(calendarEntries);
                }
                //calendarEntryRepository.deleteById(calendarEntryDelete.getId());
                calendarEntryRepository.delete(calendarEntryDelete);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
    }

    public boolean checkCalendarObject(CalendarEntry calendarEntry){
        try {
            if (calendarEntry.getName().trim().isEmpty()){
                throw new CalendarWithoutNameException("Calendar has no name!");
            }
            if(calendarEntry.getDescription().trim().isEmpty()){
                throw new NoDescriptionException("Calendar has no description!");
            }
            if (calendarEntry.getEntryStartTime() == null){
                throw new CalendarWithoutStartTimeException("Calendar has no start date!");
            }
            if (calendarEntry.getEntryFinishTime() == null){
                throw new CalendarWithoutFinishTimeException("Calendar has no finish date!");
            }
            return true;
        }
        catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return false;
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
