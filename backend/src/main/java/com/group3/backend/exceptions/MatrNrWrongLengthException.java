package com.group3.backend.exceptions;

/**
 * This exception is thrown if the length of the matrNr is wrong. It must be exactly six numbers long.
 */
public class MatrNrWrongLengthException extends Exception{
    public MatrNrWrongLengthException(String message){super(message);}
}
