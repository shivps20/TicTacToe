package com.sps.BookMyShow.controllers;

import com.sps.BookMyShow.dtos.ResponseStatus;
import com.sps.BookMyShow.dtos.SignUpRequestDTO;
import com.sps.BookMyShow.dtos.SignUpResponseDTO;
import com.sps.BookMyShow.models.User;
import com.sps.BookMyShow.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        User user = userService.signUp(signUpRequestDTO.getName(),
                signUpRequestDTO.getEmail(),
                signUpRequestDTO.getPassword());

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();

        signUpResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        signUpResponseDTO.setUser(user);

        return signUpResponseDTO;
    }
}
