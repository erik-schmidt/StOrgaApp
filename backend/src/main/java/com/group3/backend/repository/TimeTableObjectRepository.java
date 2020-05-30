package com.group3.backend.repository;

import com.group3.backend.model.TimeTableObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeTableObjectRepository extends JpaRepository<TimeTableObject, String>{
    List<TimeTableObject> findAllByFieldOfStudySemester(String courseAndSemester);
    List<TimeTableObject> findAllByCourseNumber(String courseNumber);
    @Query("SELECT t FROM TimeTableObject t WHERE t.date >= ?1 AND t.date <= ?2")
    List<TimeTableObject> findAllByDateStartEnd(LocalDate dateStart, LocalDate dateEnd);
    @Query("SELECT t FROM TimeTableObject t WHERE t.date >= ?1")
    List<TimeTableObject> findAllByDateStart(LocalDate dateStart);
    @Query("SELECT t FROM TimeTableObject t WHERE t.date >= ?1")
    List<TimeTableObject> findAllByDateEnd(LocalDate dateEnd);
}
