package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

/**
 * The repository for the {@link CalendarEntry}. Supported methods to find
 * objects are: - By description - By studentId
 */

@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    CalendarEntry findByDescription(String description);
    List<CalendarEntry> findAllByStudentId(Integer id);
    List<CalendarEntry> findCalendarEntriesByStudent_Id(int id);
}
