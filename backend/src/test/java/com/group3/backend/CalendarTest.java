package com.group3.backend;

import com.group3.backend.model.CalendarEntry;
import com.group3.backend.model.Course;
import com.group3.backend.service.CalendarEntryService;
import com.group3.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.transaction.BeforeTransaction;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CalendarTest {

    @Autowired
    private CalendarEntryService calendarEntryService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Random random;

    @Autowired
    public CalendarTest(PasswordEncoder passwordEncoder){
        random = new Random();
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeTransaction
    void init(){
        List<CalendarEntry> calendarEntries;
        calendarEntries = (List<CalendarEntry>)calendarEntryService.getAllCalendarEntries().getBody();
        System.out.println("This are the courses in the repository:");
        for(CalendarEntry c:calendarEntries){
            System.out.println(c.getName());
        }
    }

}
