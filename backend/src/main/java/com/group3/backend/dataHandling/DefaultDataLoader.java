package com.group3.backend.dataHandling;

import com.group3.backend.model.Course;
import com.group3.backend.model.News;
import com.group3.backend.model.Student;
import com.group3.backend.repository.CourseRepository;
import com.group3.backend.repository.NewsRepository;
import com.group3.backend.repository.StudentRepository;
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
        if (courseRepository.count() <= 0) {
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
    }
}