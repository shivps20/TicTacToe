package com.sps.BookMyShow.models;

import com.sps.BookMyShow.models.enums.ShowSeatStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{

    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    private Long price;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}

/**
 Whenever you see one class in another class e.g. Seat in ShowSeat
 We need to tell the Cardinality as well i.e. how Seat is related to ShowSeat
 */
