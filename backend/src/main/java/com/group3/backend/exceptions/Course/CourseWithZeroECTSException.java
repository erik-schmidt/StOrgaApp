package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} with zero ECTs.
 */
public class CourseWithZeroECTSException extends Exception{
    public CourseWithZeroECTSException(String message){super(message);}
}
