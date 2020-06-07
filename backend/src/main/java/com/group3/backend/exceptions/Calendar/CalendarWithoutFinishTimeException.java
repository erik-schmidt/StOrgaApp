package com.group3.backend.exceptions.Calendar;

import com.group3.backend.model.CalendarEntry;

/**
 * This exception is thrown if you try to create a {@link CalendarEntry} without a finish time.
 */
public class CalendarWithoutFinishTimeException extends Exception{
    public CalendarWithoutFinishTimeException(String message){super(message);}
}
