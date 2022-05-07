package com.mert.movieapp.repository;

import com.mert.movieapp.model.Director;
import com.mert.movieapp.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    
    @Query(value = "SELECT count(*) FROM user", nativeQuery = true)
    int getCount();
}