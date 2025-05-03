package com.sps.BookMyShow.models;

import com.sps.BookMyShow.models.enums.Feature;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Feature> features;

    private Long capacity;
}
