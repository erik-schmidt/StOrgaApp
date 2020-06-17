package com.group3.backend.exceptions.Calendar;

import com.group3.backend.model.CalendarEntry;

/**
 * This exception is thrown if you try to create a {@link CalendarEntry} without a date.
 */
public class CalenderDateException extends Exception {
    public CalenderDateException(String message){super(message);}
}
