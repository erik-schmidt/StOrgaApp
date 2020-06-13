package com.group3.backend.exceptions.Student;

import com.group3.backend.model.Student;

/**
 * This exception is thrown if you try to create a {@link Student}, but the matrNr is already mapped to an
 * other {@link Student}.
 */
public class StudentMatrNrIsAlreadyUsedException extends Exception{
    public StudentMatrNrIsAlreadyUsedException(String message){super(message);}
}
