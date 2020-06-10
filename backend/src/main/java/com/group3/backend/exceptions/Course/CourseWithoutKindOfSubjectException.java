package com.group3.backend.exceptions.Course;

import com.group3.backend.model.Course;

/**
 * This exception is thrown if you try to create a {@link Course} without a kind of subject.
 */
public class CourseWithoutKindOfSubjectException extends Exception{
    public CourseWithoutKindOfSubjectException(String message){
        super(message);
    }
}
