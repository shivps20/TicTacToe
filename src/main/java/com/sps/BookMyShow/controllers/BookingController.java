package com.sps.BookMyShow.controllers;

import com.sps.BookMyShow.dtos.BookingRequestDTO;
import com.sps.BookMyShow.dtos.BookingResponseDTO;
import com.sps.BookMyShow.dtos.ResponseStatus;
import com.sps.BookMyShow.models.Booking;
import com.sps.BookMyShow.services.BookingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private BookingService bookingService;

    /**
     * If you want to inject the dependency in some other class, just give me that object in the constructor
     * @param bookingService
     */
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/book")  //To make this method act as an API
    public BookingResponseDTO bookTicket(@RequestBody BookingRequestDTO bookingRequestDTO){

        BookingResponseDTO bookingResponseDTO = new BookingResponseDTO();

        try {
            Booking booking = bookingService.bookTicket(bookingRequestDTO.getUserId(),
                    bookingRequestDTO.getShowId(),
                    bookingRequestDTO.getShowSeatsIds());
            bookingResponseDTO.setTicket(booking);
            bookingResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            bookingResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }


        return bookingResponseDTO;
    }

}

/*
Dependency injection

You can add annotations to classes to make that class a special class
Spring will manage it's objects - Spring beans
Objects managed by Spring ae known as Spring Beans
 */
