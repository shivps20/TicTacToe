package com.sps.BookMyShow.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptPasswordEncoderConfig{

    @Bean
    public BCryptPasswordEncoder getEncoder(){

        return new BCryptPasswordEncoder();
    }
}

/*
Beans are spring objects
 */