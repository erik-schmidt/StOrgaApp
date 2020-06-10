package com.group3.backend.exceptions.GradCourseMapping;
import com.group3.backend.model.GradeCourseMapping;

/**
 * This exception is thrown if you try to create a {@link GradeCourseMapping} without a number.
 */
public class GradeCourseMappingWithoutNumberException extends Exception{
    public GradeCourseMappingWithoutNumberException(String message){
        super(message);
    }
}
