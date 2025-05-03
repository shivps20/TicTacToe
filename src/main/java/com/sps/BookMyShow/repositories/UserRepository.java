package com.sps.BookMyShow.repositories;

import com.sps.BookMyShow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*
    JPA methods are like inbuilt methods which you can directly use to query your database

    select * from user where id = :userId
     */

    Optional<User> findByEmail(String email);

}
