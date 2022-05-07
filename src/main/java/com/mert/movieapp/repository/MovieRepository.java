package com.mert.movieapp.repository;

import com.mert.movieapp.model.Movie;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface MovieRepository extends CrudRepository<Movie, Integer> {
    
    @Query(value = "SELECT count(*) FROM movie", nativeQuery = true)
    int getCount();

    @Query(value = "SELECT * FROM movie where category_id = :id", nativeQuery = true)
    Iterable<Movie> findByCategoryId(int id);

    @Query(value = "SELECT * FROM movie order by release_date desc limit 12", nativeQuery = true)
    Iterable<Movie> getMoviesDateDesc();

    @Query(value = "SELECT * FROM movie order by imdb desc limit 20", nativeQuery = true)
    Iterable<Movie> getTopRated();

    @Query(value = "SELECT * FROM movie order by imdb desc limit 40", nativeQuery = true)
    Iterable<Movie> getImdbTop50();

    @Query(value = "SELECT * FROM movie where title like %?1%", nativeQuery = true)
    Iterable<Movie> searchMovies(String text);
}