package com.group3.backend.exceptions;

/**
 * This exception is thrown if the syntax of the matrNr is wrong. It must contain only numbers.
 */
public class MatrNrWrongSyntaxException extends Exception{
    public MatrNrWrongSyntaxException(String message){super(message);}
}
