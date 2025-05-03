package com.sps.BookMyShow.services;

import com.sps.BookMyShow.models.User;
import com.sps.BookMyShow.repositories.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User signUp(String name,
                       String email,
                       String password) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new RuntimeException("User with this email is already present");
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        //
        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        return user;
    }
}