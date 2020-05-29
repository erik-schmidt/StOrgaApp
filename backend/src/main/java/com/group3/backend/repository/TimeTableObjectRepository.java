package com.group3.backend.repository;

import com.group3.backend.model.TimeTableObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TimeTableObjectRepository extends JpaRepository<TimeTableObject, String>{
    List<TimeTableObject> findAllByStartTimeDate(LocalDateTime startTimeDate);
    List<TimeTableObject> findAllByFieldOfStudyAndSemester(String courseAndSemester);
    List<TimeTableObject> findAllByStartTimeDateAndFinishTimeDateIsBetween(LocalDateTime startTimeDate, LocalDateTime finishTimeDate);
    List<TimeTableObject> findAllByCourseNumber(String courseNumber);
}
