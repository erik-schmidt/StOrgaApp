package com.group3.backend;

import com.group3.backend.controller.CourseController;
import com.group3.backend.model.Course;
import com.group3.backend.model.Student;
import com.group3.backend.service.CourseService;
import com.group3.backend.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void testAddingStudent() {
        studentService.createStudent(new Student("202481", "Liyan", "Fu-Wacker", "AIB", 7));
        List<Student> studentSet = (List<Student>)studentService.getAllStudents().getBody();
        Assertions.assertFalse(studentSet.isEmpty());
    }
}
