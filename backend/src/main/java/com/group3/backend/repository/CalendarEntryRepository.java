package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    List<CalendarEntry> findCalendarEntriesByStudent_Id(int id);
}
