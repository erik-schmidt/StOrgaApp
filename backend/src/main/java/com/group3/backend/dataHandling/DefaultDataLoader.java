package com.group3.backend.dataHandling;

import com.group3.backend.model.Course;
import com.group3.backend.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Set;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DefaultDataLoader implements ApplicationListener<ApplicationReadyEvent> {
    @NonNull
    private final CourseRepository courseRepository;

    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (courseRepository.count() > 0) {
            return;
        }

        DataHandler dataHandler = new DataHandler();
        Set<Course> courseSet = dataHandler.loadCourses();
        for (Course course : courseSet) {
            courseRepository.save(course);
        }
    }
}