package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a professor.
 */
public class CourseWithoutProfessorException extends Exception{
    public CourseWithoutProfessorException(String message){super(message);}
}
