package com.mert.movieapp.repository;

import com.mert.movieapp.model.Actor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ActorRepository extends CrudRepository<Actor, Integer> {
    @Query(value = "SELECT * FROM actor where name = :name", 
    nativeQuery = true)
Actor findByName(String name);

    
}