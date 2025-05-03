package com.sps.BookMyShow.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){

        super(message);
    }
}
