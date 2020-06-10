package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to search for a {@link Student} by its matrNr but no mapped {@link Student}
 * could be found.
 */
public class StudentDoesntMatchToMatrNrException extends Exception{
    public StudentDoesntMatchToMatrNrException(String message){
        super(message);
    }
}
