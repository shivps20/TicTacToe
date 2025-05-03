package com.sps.BookMyShow.exceptions;

public class SeatsNoLongerAvailableException extends RuntimeException{
    public SeatsNoLongerAvailableException(String message){
        super(message);
    }
}
