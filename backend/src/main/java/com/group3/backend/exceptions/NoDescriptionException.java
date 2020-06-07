package com.group3.backend.exceptions;

/**
 * This exception is thrown if you want to create an object without a description.
 */
public class NoDescriptionException extends Exception{
    public NoDescriptionException(String message){super(message);}
}
