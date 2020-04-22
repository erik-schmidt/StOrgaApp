package com.group3.backend.repository;

import com.group3.backend.model.CalendarEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CalendarEntryRepository extends JpaRepository<CalendarEntry, String> {
    //CalendarEntry findById(int id);
    CalendarEntry findByDescription(String description);
    Set<CalendarEntry> findAllByDescription(String description);
}
