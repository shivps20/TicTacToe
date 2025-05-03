package com.sps.BookMyShow.dtos;


import com.sps.BookMyShow.models.Booking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDTO {
    private Booking ticket;
    private ResponseStatus responseStatus;  //enum
}
