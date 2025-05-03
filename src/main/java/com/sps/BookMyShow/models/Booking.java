package com.sps.BookMyShow.models;

import com.sps.BookMyShow.models.enums.BookingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{
    private String no;
    private BookingStatus bookingStatus;

    @ManyToMany
    private List<ShowSeat> showSeats;
    /**
     One Booking can have multiple ShowSeats
     One ShowSeat can belong to multiple Bookings
     e.g. For different shows, same seat can be available
     show_id=1, seat_id=2
     show_id=1 seat_id=3
     show_id=1 seat_id=4
     */

    @OneToMany  //Payment in multiple modes e.g. Card + UPI
    private List<Payment> payments;

    @ManyToOne
    private User user;
    /**
     One Booking will be made by one user. One User can have multiple bookings
     */

    private Long amount;
}

