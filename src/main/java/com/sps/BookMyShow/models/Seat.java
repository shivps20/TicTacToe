package com.sps.BookMyShow.models;

import com.sps.BookMyShow.models.enums.SeatType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel{
    private String no;
    private SeatType seatType;

    @ManyToOne
    private Screen screen; //depends on the query pattern
}
