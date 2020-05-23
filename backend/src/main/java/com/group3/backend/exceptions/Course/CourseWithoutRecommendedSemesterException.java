package com.group3.backend.exceptions.Course;

public class CourseWithoutRecommendedSemesterException extends Exception{
    public CourseWithoutRecommendedSemesterException(String message)
    {
        super(message);
    }
}
