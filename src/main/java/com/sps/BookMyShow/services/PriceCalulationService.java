package com.sps.BookMyShow.services;


import com.sps.BookMyShow.models.ShowSeat;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PriceCalulationService {

    public  Long calculcatePrice(List<ShowSeat> showSeats){

        Long amount = 0L;

        for(ShowSeat showSeat:  showSeats){
            amount += showSeat.getPrice();
        }

        return amount;
    }
}
