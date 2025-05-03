package com.sps.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {
    @Id  //Primary Key Marking
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This is to ensure that the value for ID is auto generated in DB for the new record
    private Long id;

    @CreatedDate
    private Long createdAt; //epoch

    @LastModifiedDate
    private Long lastModifiedAt;
}

/*
We also need to represent relations/cardinalities in the entities so that
springboot can create aN optimal schema for my class diagram

In next class:- ORM (Please read about (ORM-Hibernate) before coming to next class)
 */