package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to create a {@link Student}, but the family name has the wrong
 * syntax (numbers, special character...).
 */
public class StudentFamilynameWrongSyntaxException extends Exception{
    public StudentFamilynameWrongSyntaxException(String message){super(message);}
}
