package com.group3.backend.exceptions.GradCourseMapping;

import com.group3.backend.model.GradeCourseMapping;
import com.group3.backend.model.Course;

/**
 * This exception is thrown if your {@link GradeCourseMapping} has a course number which can not be mapped to a
 * existing {@link Course}.
 */
public class GradeCourseMappingCourseNotFoundException extends Exception{
    public GradeCourseMappingCourseNotFoundException(String message){
        super(message);
    }
}
