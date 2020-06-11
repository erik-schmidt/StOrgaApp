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

    /**
     * Is used to test the reachability of the service.
     * Called by "/ping".
     * @return
     *          "reachable" to represent that the service can be reached.
     */
    public String ping() {
        return "reachable";
    }

    /**
     * Is used to get all {@link CalendarEntry} from the repository.
     * Called by "/getAll".
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link CalendarEntry} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getAllCalendarEntries(){
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntries);
    }

    /**
     * Is used to get all {@link CalendarEntry} from a specific {@link Student}.
     * Called by "/{matrNr}/get".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want the {@link CalendarEntry} objects for.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link CalendarEntry} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> getStudentCalendarEntries (String matrNr) {
        try{
            checkMatriculationNumber(matrNr);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        Student student = (Student)studentService.getStudentByNumber(matrNr).getBody();
        List<CalendarEntry> calendarEntries = calendarEntryRepository.findAllByStudentId(student.getId());
        return ResponseEntity.status(HttpStatus.OK).body(calendarEntries);
    }

    /**
     * Is used to map a {@link CalendarEntry} to a {@link Student}.
     * Called by "/{matrNr}/create".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want map the {@link CalendarEntry} objects to.
     * @param calendarEntry
     *                      The {@link CalendarEntry} object which should be mapped to the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get a list of
     *          {@link CalendarEntry} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<?> createCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        try {
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

    /**
     * Is used to delete a {@link CalendarEntry} from a {@link Student}.
     * Called by "/{matrNr}/delete".
     * @param matrNr
     *                  The matrNr of the {@link Student} you want to delete the {@link CalendarEntry} objects from.
     * @param calendarEntry
     *                      The {@link CalendarEntry} you want to delete from the {@link Student}.
     * @return
     *          Returns a ResponseEntity. If the request was successful, the HTTPStatus is 'OK' and you get the deleted
     *          {@link CalendarEntry} in its body.
     *          If the request wasn't successful you get a HTTPStatus 'BAD-REQUEST'.
     */
    public ResponseEntity<CalendarEntry> deleteCalendarEntry(String matrNr, CalendarEntry calendarEntry){
        try {
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
                calendarEntryRepository.delete(calendarEntryDelete);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        catch (Exception e){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getClass() + " " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CalendarEntry());
    }

    /**
     * Method to check if a {@link CalendarEntry} has a valid syntax.
     * @param calendarEntry
     *                      The {@link CalendarEntry} you want to check.
     * @return
     *          Returns true if the {@link CalendarEntry} has a valid syntax and false if it hasn't.
     */
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

//    /**
//     * Method to check if a matrNr is used by a saved {@link Student}.
//     * @param matrNr
//     *                  The matrNr which should be checked.
//     * @return
//     *          Returns true if the matrNr is used by a saved {@link Student}.
//     * @throws Exception
//     *                      Is thrown if no saved {@link Student} uses this matrNr.
//     */
//    private boolean checkStudentWithNumberIsSaved(String matrNr) throws Exception{
//        Student st = studentRepository.findByMatrNr(matrNr);
//        if(st == null){
//            logger.error("No Student with the given matriculation number "+ matrNr + " found");
//            throw new MatrNrException("Kein Student mit dieser Matrikelnummer gefunden");
//        }
//        return true;
//    }
}
