package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a number.
 */
public class CourseWithoutNumberException extends Exception{
    public CourseWithoutNumberException(String message){super(message);}
}
