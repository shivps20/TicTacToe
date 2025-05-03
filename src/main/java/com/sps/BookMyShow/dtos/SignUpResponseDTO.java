package com.sps.BookMyShow.dtos;

import com.sps.BookMyShow.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDTO {
    private User user;
    private ResponseStatus responseStatus;
}
