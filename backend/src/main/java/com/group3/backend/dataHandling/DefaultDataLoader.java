package com.group3.backend.dataHandling;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.model.Course;
import com.group3.backend.model.News;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CalendarEntryRepository;
import com.group3.backend.model.TimeTableObject;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.NewsRepository;
import com.group3.backend.repository.StudentRepository;
import com.group3.backend.repository.TimeTableObjectRepository;
import com.group3.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultDataLoader implements ApplicationListener<ApplicationReadyEvent> {
    @NonNull
    private final CourseRepository courseRepository;
    @NonNull
    private final StudentRepository studentRepository;
    @NonNull
    private StudentService studentService;
    @NonNull
    private final NewsRepository newsRepository;
    @NonNull
    private final CalendarEntryRepository calendarEntryRepository;
    @NonNull
    private TimeTableObjectRepository timeTableObjectRepository;

    /**
     * load the standard informations with {@link DataHandler} and save it in the
     * repositories
     * 
     * @param event
     */
    public void onApplicationEvent(ApplicationReadyEvent event) {
        DataHandler dataHandler = new DataHandler();
        // load and save admin user
        Student student = dataHandler.loadAdminUser();
        if (studentRepository.findByMatrNr(student.getMatrNr()) == null) {
            studentService.createStudent(student);
        }
        // load and save default courses
        if (courseRepository.count() == 0) {
            Set<Course> courseSet = dataHandler.loadCourses();
            for (Course course : courseSet) {
                courseRepository.save(course);
            }
        }
        if (newsRepository.count() <= 0) {
            Set<News> newsSet = dataHandler.loadNews();
            for (News news : newsSet) {
                newsRepository.save(news);
            }
        }
        if (calendarEntryRepository.count() <= 0) {
            Set<CalendarEntry> calendarEntries = dataHandler.loadCalendarEntries();

            student.setCalendarEntries(calendarEntries);

            for (CalendarEntry calendarEntry : calendarEntries) {
                calendarEntryRepository.save(calendarEntry);
            }

            // calendarEntryRepository.save(calendarEntries);

        }
        // load and save timetable
        if (timeTableObjectRepository.count() == 0) {
            List<TimeTableObject> timeTableObjectSet = dataHandler.loadTimeTable();
            for (TimeTableObject timeTableObject : timeTableObjectSet) {
                timeTableObjectRepository.save(timeTableObject);
            }
        }
    }
}