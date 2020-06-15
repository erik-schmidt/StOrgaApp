package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a room.
 */
public class CourseWithoutRoomException extends Exception{
    public CourseWithoutRoomException(String message){super(message);}
}
