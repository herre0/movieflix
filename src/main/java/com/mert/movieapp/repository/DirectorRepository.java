package com.mert.movieapp.repository;

import com.mert.movieapp.model.Director;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DirectorRepository extends CrudRepository<Director, Integer> {


    @Query(value = "SELECT * FROM director where name = :name", 
        nativeQuery = true)
    Director findByName(String name);

    @Query(value = "SELECT id, name, birth_date, surname, picture, bio FROM director", 
        nativeQuery = true)
    Iterable<Director> getDirectors();
    
}