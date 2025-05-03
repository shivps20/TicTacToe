package com.sps.BookMyShow.exceptions;

public class ShowSeatsNotValidException extends RuntimeException{
    public ShowSeatsNotValidException(String message){

        super(message);
    }
}
