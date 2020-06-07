package com.group3.backend.exceptions.LinkList;

import com.group3.backend.model.Link;

/**
 * This exception is thrown if you try to search for a {@link Link} by its linkID and the ID is empty or {@code 0}.
 */
public class LinkedListWithoutLinkIDException extends Exception{
    public LinkedListWithoutLinkIDException(String message){
        super(message);
    }
}
