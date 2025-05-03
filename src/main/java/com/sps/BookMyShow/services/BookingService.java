package com.sps.BookMyShow.services;

import com.sps.BookMyShow.exceptions.SeatsNoLongerAvailableException;
import com.sps.BookMyShow.exceptions.ShowNotFoundException;
import com.sps.BookMyShow.exceptions.ShowSeatsNotValidException;
import com.sps.BookMyShow.exceptions.UserNotFoundException;
import com.sps.BookMyShow.models.Booking;
import com.sps.BookMyShow.models.Show;
import com.sps.BookMyShow.models.ShowSeat;
import com.sps.BookMyShow.models.User;
import com.sps.BookMyShow.models.enums.BookingStatus;
import com.sps.BookMyShow.models.enums.ShowSeatStatus;
import com.sps.BookMyShow.repositories.ShowRepository;
import com.sps.BookMyShow.repositories.ShowSeatRepository;
import com.sps.BookMyShow.repositories.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;

    private PriceCalulationService priceCalulationService;

    private Lock lock;

    public BookingService(UserRepository userRepository,
                          ShowSeatRepository showSeatRepository,
                          ShowRepository showRepository,
                          PriceCalulationService priceCalulationService){
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalulationService = priceCalulationService;
        this.lock = new ReentrantLock();
    }

    @Transactional
    public Booking bookTicket(Long userId, Long showId, List<Long> showSeatIds){

        //{1,2,3,4,5} - Lets say in the Booking request 5 seats has been requested

        //Step 1 - First perform the Validation of the data. For Validation, since it needs DB interaction use repository classes
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User is not valid");
        }

        Optional<Show> optionalShow = showRepository.findById(showId);

        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show selected is not valid");
        }

        User user = optionalUser.get();
        Show show = optionalShow.get();

        List<ShowSeat> showSeats =  showSeatRepository.findAllShowSeatAndLock(showSeatIds); //{1,2,3,4,5}

        if(showSeats.size() != showSeatIds.size()){
            throw new ShowSeatsNotValidException("All show seats are not valid");
        }


        for(ShowSeat showSeat: showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)){
                throw new SeatsNoLongerAvailableException("Some seats are no longer available");
            }
        }

        //Step 2: Once all paramaters are validated and everything looks good, then proceed with the booking process

        Booking booking = new Booking();

        //as of now booking status is pending
        //once payment is confirmed, change booking status to confirmed
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setNo("BOOKING_" + userId + " Show_" + showId);
        booking.setUser(user);

        //give 10 min to the user to make the payment??
        //in these 10 min, will you want any other to select those showSeats
        //and proceed for booking?

        //before you proceed for payment, you'll mark all seats as blocked
        //either you can lock the seats directly in the table using SQL transactions(ACID)
        //Isolation level (RC, RU, RR, Serializable)


        //Before the payment is done, mark all seats as blocked. You can do this in 2 ways -
        //1 - You can lock the seats directly in the table using SQL transaction (ACID) : Isolation level (RC, RU, RR, Serializable)
        //2 - Via code lock
        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
        }
        showSeatRepository.saveAll(showSeats); //Updated in DB, all seats blocked
        /*
        //Also, you need to ensure that this code to block the seats should be threadsafe so that the same seats are not blocked by 2 diff users
        e.g.
        User 1 = {1,2} ------- T1
        User 2 = {2,3,5} ------ T2

        Via Application level lock, we can use locks for the given section of code (e.g. lock.lock() and lock.unlock()), this way it will
        ensure that only thread enters the code block and does the execution.
        But this has a problem. Lets say User1 and User2 are trying to book different set of seats. In that case also, this code will not allow
        2nd user to enter till user1 process is completed. Hence we should not handle this at code level, but at DB level
        Also, in a large environment you will have multiple servers and each server will have its lock. Hence in such distributed environments
        locking mechanism at Application level will not help, as it is local memory thing. Hence, DB level lock is more ideal approach

        In this case we can achieve that via the Repository class
         */

        booking.setShowSeats(showSeats);
        booking.setAmount(priceCalulationService.calculcatePrice(showSeats));

        //now you should start the payment flow, get the payment confirmation
        //and confirm the user that booking is confirmed
        //if the payment fails, rollback everything that you did till now
        //3rd party payment gateway - out of scope

        //assume you payment is confirmed
        booking.setBookingStatus(BookingStatus.CONFIRMED);

        for(ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BOOKED);
        }
        showSeatRepository.saveAll(showSeats);


        /*
        unlock the showSeat rows - HomeWork-1 line change
        just give this code to GPT and ask him to unlock the rows at the end
         */

        /*
        IF PAYMENT FAILS => what is rollback that I did above? read about it
         */

        return booking;

    }
}
