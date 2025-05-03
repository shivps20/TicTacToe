package com.sps.BookMyShow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel {
    private String name;

    @OneToMany  //One "City" can have "Multiple" Theatres i.e. City:Theatre = 1:m
    private List<Theatre> theatres;
}
