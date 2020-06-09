package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to create a {@link Student}, but the pre name has the wrong length.
 */
public class StudentPrenameWithWrongLengthException extends Exception{
    public StudentPrenameWithWrongLengthException(String message){super(message);}
}
