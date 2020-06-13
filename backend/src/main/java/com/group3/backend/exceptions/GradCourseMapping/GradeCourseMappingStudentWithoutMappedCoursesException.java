package com.group3.backend.exceptions.GradCourseMapping;

import com.group3.backend.model.Student;
import com.group3.backend.model.GradeCourseMapping;

/**
 * This exception is thrown if your {@link Student} has no mapped {@link GradeCourseMapping} objects.
 */
public class GradeCourseMappingStudentWithoutMappedCoursesException extends Exception{
    public GradeCourseMappingStudentWithoutMappedCoursesException(String message){super(message);}
}
