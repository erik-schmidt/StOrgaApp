package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to create a {@link Student}, but the family name has the wrong length.
 */
public class StudentFamilynameWithWrongLengthException extends Exception{
    public StudentFamilynameWithWrongLengthException(String message){super(message);}
}
