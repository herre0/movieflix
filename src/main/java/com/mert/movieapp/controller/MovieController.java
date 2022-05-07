package com.mert.movieapp.controller;


import java.util.ArrayList;
import java.util.List;

import com.mert.movieapp.model.Actor;
import com.mert.movieapp.model.Category;
import com.mert.movieapp.model.Director;
import com.mert.movieapp.model.Movie;
import com.mert.movieapp.repository.ActorRepository;
import com.mert.movieapp.repository.CategoryRepository;
import com.mert.movieapp.repository.DirectorRepository;
import com.mert.movieapp.repository.MovieRepository;
import com.mert.movieapp.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;


@RestController
public class MovieController {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final ActorRepository actorRepository;
    private final DirectorRepository directorRepository;
    private final UserRepository userRepository;

    public MovieController(MovieRepository movieRepository,UserRepository userRepository,DirectorRepository directorRepository,ActorRepository actorRepository,CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.actorRepository =actorRepository;
        this.directorRepository =directorRepository;
        this.userRepository =userRepository;
    }


    @GetMapping("/movie")
    public Iterable<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/moviecount")
    public int getMoviesCount() {
        return movieRepository.getCount();
    }
    @GetMapping("/usercount")
    public int getUsersCount() {
        return userRepository.getCount();
    }

    @GetMapping("/movie/{movieId}")
    public Movie getMovieById(@PathVariable int movieId) {
        return movieRepository.findById(movieId).get();
    }

    @GetMapping("/movies/{categoryId}")
    public Iterable<Movie> getMovieByCategoryId(@PathVariable int categoryId) {
        return movieRepository.findByCategoryId(categoryId);
    }

    @GetMapping("/movies/thisweek")
    public Iterable<Movie> getMoviesDateDesc() {
        return movieRepository.getMoviesDateDesc();
    }

    @GetMapping("/movies/toprated")
    public Iterable<Movie> getTopRatedMovies() {
        return movieRepository.getTopRated();
    }

    @GetMapping("/movies/imdb50")
    public Iterable<Movie> getImdb50() {
        return movieRepository.getImdbTop50();
    }

    @GetMapping("/movies/search/{searchText}")
    public Iterable<Movie> getSearchedMovies(@PathVariable String searchText) {
        return movieRepository.searchMovies(searchText);
    }

    @PostMapping("/movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie) {
        Category cat = categoryRepository.findById(movie.getCategory().getCategoryId()).get();
        List<Actor> actorList = new ArrayList<>();
        List<Director> directorList = new ArrayList<>();
        for(Actor actor: movie.getCast()){
            Actor realActor = actorRepository.findById(actor.getId()).get();
            actorList.add(realActor);
        }

        for(Director direct: movie.getDirectors()){
            Director realDirector = directorRepository.findById(direct.getId()).get();
            directorList.add(realDirector);
        }
        movie.setDirectors(directorList);
        movie.setCast(actorList);        
        
        movie.setCategory(cat);

        movieRepository.save(movie);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The movie is inserted successfully");
    }

    @PostMapping("/movie/{movieId}")
    public ResponseEntity<String> updateMovie(@PathVariable int movieId, @RequestBody Movie movie) {
        Category cat = categoryRepository.findById(movie.getCategory().getCategoryId()).get();
        List<Actor> actorList = new ArrayList<>();
        List<Director> directorList = new ArrayList<>();
        for(Actor actor: movie.getCast()){
            Actor realActor = actorRepository.findById(actor.getId()).get();
            actorList.add(realActor);
        }

        for(Director direct: movie.getDirectors()){
            Director realDirector = directorRepository.findById(direct.getId()).get();
            directorList.add(realDirector);
        }
         

        Movie oldMovie = movieRepository.findById(movieId).get();

        oldMovie.setCast(actorList);
        oldMovie.setCategory(cat);
        oldMovie.setDescription(movie.getDescription());
        oldMovie.setDirectors(directorList);
        oldMovie.setImage(movie.getImage());
        oldMovie.setTrailer(movie.getTrailer());
        oldMovie.setImdb(movie.getImdb());
        oldMovie.setReleaseDate(movie.getReleaseDate());
        oldMovie.setTitle(movie.getTitle());

        movieRepository.save(oldMovie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The new movie was updated successfully.");
    }
}