package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} with a description which already exists.
 */
public class CourseDescriptionAlreadyExistsException extends Exception {
    public CourseDescriptionAlreadyExistsException(String message){super(message);}
}
