package com.group3.backend.exceptions.Course;

public class CourseWithoutKindOfSubjectException extends Exception{
    public CourseWithoutKindOfSubjectException(String message){
        super(message);
    }
}
