package com.group3.backend.exceptions.Course;

public class CourseWithoutFieldOfStudyException extends Exception {
    public CourseWithoutFieldOfStudyException(String message)
    {
        super(message);
    }
}
