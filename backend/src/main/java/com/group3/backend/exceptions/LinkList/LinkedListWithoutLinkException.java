package com.group3.backend.exceptions.LinkList;

import com.group3.backend.model.Link;

/**
 * This exception is thrown if you try to create a {@link Link} without a link.
 */
public class LinkedListWithoutLinkException extends Exception{
    public LinkedListWithoutLinkException(String message){super(message);}
}
