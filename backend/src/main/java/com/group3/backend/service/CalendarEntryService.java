package com.group3.backend.service;

import com.group3.backend.exceptions.*;
import com.group3.backend.exceptions.Calendar.CalenderDateException;
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
        try{
            checkStudentWithNumberIsSaved(matrNr);
            checkMatriculationNumber(matrNr);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAllByStudentId(student.getId());
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntries);
    }

    public ResponseEntity<?> createCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        try {
            checkStudentWithNumberIsSaved(matrNr);
            checkCalendarObject(calendarEntry);
            checkMatriculationNumber(matrNr);
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
            checkStudentWithNumberIsSaved(matrNr);
            checkCalendarObject(calendarEntry);
            checkMatriculationNumber(matrNr);
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

    private boolean checkCalendarObject(CalendarEntry calendarEntry){
        try {
            if (calendarEntry.getName().trim().isEmpty()||calendarEntry.getName()==null){
                logger.error("Calender has no name");
                throw new CalendarWithoutNameException("Kalendereintrag hat keinen Namen!");
            }
            if(calendarEntry.getDescription().trim().isEmpty()||calendarEntry.getDescription()==null){
                logger.error("Calender has no description");
                throw new NoDescriptionException("Kalendereintrag hat keine beschreibung!");
            }
            if (calendarEntry.getEntryStartTime() == null){
                logger.error("Calender has no start date");
                throw new CalendarWithoutStartTimeException("Kalendereintrag hat kein Startdatum!");
            }
            if (calendarEntry.getEntryFinishTime() == null){
                logger.error("Calender has no finish date");
                throw new CalendarWithoutFinishTimeException("Kalendereintrag hat kein Enddatum!");
            }
            if(calendarEntry.getEntryStartTime().isAfter(calendarEntry.getEntryFinishTime())){
                logger.error("calender entry Startdate after Enddate! ");
                throw new CalenderDateException("Kalendereintrag Startzeit ist nach Endzeit");
            }
            return true;
        }
        catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return false;
    }

    /**
     * checks if a given Matriculation number is used by a saved student
     * @param matrNr number to check
     * @return String
     * @throws Exception matriculation number Exception
     */
    private void checkStudentWithNumberIsSaved(String matrNr) throws Exception{
        Student st = studentRepository.findByMatrNr(matrNr);
        if(st == null){
            logger.error("No Student with the given matriculation number "+ matrNr + " found");
            throw new MatrNrException("Kein Student mit dieser Matrikelnummer gefunden");
        }
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
