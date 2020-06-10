package com.group3.backend.exceptions.Calendar;

import com.group3.backend.model.CalendarEntry;

/**
 * This exception is thrown if you try to create a {@link CalendarEntry} without a start time.
 */
public class CalendarWithoutStartTimeException extends Exception{
    public CalendarWithoutStartTimeException(String message){super(message);}
}
