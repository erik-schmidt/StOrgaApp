package com.group3.backend.exceptions.Calendar;

import com.group3.backend.model.CalendarEntry;

/**
 * This exception is thrown if you try to create a {@link CalendarEntry} without a name.
 */
public class CalendarWithoutNameException extends Exception{
    public CalendarWithoutNameException(String message){super(message);}
}
