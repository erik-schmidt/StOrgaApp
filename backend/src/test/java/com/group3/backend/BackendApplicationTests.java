package com.group3.backend;

import com.group3.backend.controller.CourseController;
import com.group3.backend.model.Course;
import com.group3.backend.service.CourseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private CourseService courseService;

    @Test
    void testAddingCourse() {
        courseService.createCourse(new Course("AIB", "420", "SWELab", "A007", "Prof.Haag", 7, "choose", 9, "test"));
        Assertions.assertFalse(courseService.getAllCourses().isEmpty());
    }

}
