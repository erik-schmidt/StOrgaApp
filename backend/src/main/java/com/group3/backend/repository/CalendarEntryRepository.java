package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    List<CalendarEntry> findCalendarEntriesByStudent_Id(int id);
    CalendarEntry findById(int id);

   @Query("SELECT c FROM CalendarEntry c WHERE c.student.id = ?1 AND c.entryDate >= ?2 AND c.entryDate <= ?3")
    List<CalendarEntry> findCalendarEntriesByStudent_IdAndEntryDateAndEntryDate(int id, LocalDate dateStart, LocalDate dateEnd);


}
