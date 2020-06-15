package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a study focus.
 */
public class CourseWithoutStudyFocusException extends Exception{
    public CourseWithoutStudyFocusException(String message){super(message);}
}
