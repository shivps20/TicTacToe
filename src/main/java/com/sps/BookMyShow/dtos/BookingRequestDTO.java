package com.sps.BookMyShow.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookingRequestDTO {
    private Long userId;
    private List<Long> showSeatsIds;
    private Long showId;
}
