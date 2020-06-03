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
    List<CalendarEntry> findAllByStudentId(Integer id);
}
