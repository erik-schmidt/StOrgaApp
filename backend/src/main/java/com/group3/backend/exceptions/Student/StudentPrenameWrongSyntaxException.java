package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to create a {@link Student}, but the pre name has the wrong
 * syntax (numbers, special character...).
 */
public class StudentPrenameWrongSyntaxException extends Exception{
    public StudentPrenameWrongSyntaxException(String message){super(message);}
}
