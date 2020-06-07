package com.group3.backend.exceptions;

import com.group3.backend.model.GradeCourseMapping;

/**
 * This exception is thrown if you try to create a {@link GradeCourseMapping} and the grade has the wrong format.
 */
public class GradeFormatException extends Exception{
    public GradeFormatException(String message){
        super(message);
    }
}
