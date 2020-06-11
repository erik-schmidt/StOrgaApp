package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


/**
 * The repository for the {@link CalendarEntry}. Supported methods to find
 * objects are: - By description - By studentId
 */

@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    CalendarEntry findByDescription(String description);
    List<CalendarEntry> findAllByStudentId(Integer id);
    List<CalendarEntry> findCalendarEntriesByStudent_Id(int id);
    CalendarEntry findById(int id);
    @Query("SELECT c.id FROM CalendarEntry c WHERE c.id = (SELECT MAX(id) FROM CalendarEntry )")
    int findCalendarEntryByMaxId();

   @Query("SELECT c FROM CalendarEntry c WHERE c.student.matrNr = ?1 AND c.entryDate >= ?2 AND c.entryDate <= ?3")
    List<CalendarEntry> findCalendarEntriesByStudent_IdAndEntryDateAndEntryDate(String matrNr, LocalDate dateStart, LocalDate dateEnd);


}
