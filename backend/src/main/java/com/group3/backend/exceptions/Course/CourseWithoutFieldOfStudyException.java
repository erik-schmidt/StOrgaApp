package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a field of study.
 */
public class CourseWithoutFieldOfStudyException extends Exception {
    public CourseWithoutFieldOfStudyException(String message)
    {
        super(message);
    }
}
