package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} with a course number which already exists.
 */
public class CourseNumberAlreadyExistsException extends Exception {
    public CourseNumberAlreadyExistsException(String message){super(message);}
}
