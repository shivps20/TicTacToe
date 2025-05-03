package com.sps.BookMyShow.exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String message){

        super(message);
    }
}
