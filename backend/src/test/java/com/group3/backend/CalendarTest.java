package com.group3.backend;

import com.group3.backend.service.CalendarEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalendarTest {

    @Autowired
    private CalendarEntryService calendarEntryService;


}
