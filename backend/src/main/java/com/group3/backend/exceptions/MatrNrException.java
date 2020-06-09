package com.group3.backend.exceptions;

/**
 * This exception is thrown if either the length or the format of the matrNr is wrong.
 */
public class MatrNrException extends Exception{
    public MatrNrException(String message){super(message);}
}
