package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a recommended semester.
 */
public class CourseWithoutRecommendedSemesterException extends Exception{
    public CourseWithoutRecommendedSemesterException(String message)
    {
        super(message);
    }
}
