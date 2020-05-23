package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    //@Query(value = "SELECT * FROM CalendarEntry WHERE "
    //CalendarEntry findByStudAndId(String matrNr, int id);
    CalendarEntry findByDescription(String description);
    List<CalendarEntry> findAllByStudentId(Integer id);
    //CalendarEntry findByDescription(String matrNr, String description);

    //List<CalendarEntry> findByDate(String matrNr, Date date);

    // TODO: 24.04.2020 : getAllEntriesOfWeek und getAllEntriesOfMonth damit die DB nicht überlastet wird. Dementsprechend wird dateOfEntry zu einem Datumsobjekt
    // TODO: 24.04.2020 : Tests schreiben
}
