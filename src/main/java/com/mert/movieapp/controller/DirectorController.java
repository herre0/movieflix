package com.mert.movieapp.controller;


import java.util.ArrayList;
import java.util.List;

import com.mert.movieapp.model.Director;
import com.mert.movieapp.repository.DirectorRepository;

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
public class DirectorController {

    private final DirectorRepository directorRepository;

    public DirectorController(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @GetMapping("/director")
    public Iterable<Director> getDirector() {
        Iterable<Director> directors = directorRepository.getDirectors();        

        return directors;
    }

    @GetMapping("/director/{directorId}")
    public Director getDirectorById(@PathVariable int directorId) {
        return directorRepository.findById(directorId).get();
    }

    @PostMapping("/director")
    public ResponseEntity<String> addDirector(@RequestBody Director director) {
        director.setName(director.getName().trim());
        directorRepository.save(director);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The director is inserted successfully");
    }

    @PostMapping("/director/{directorId}")
    public ResponseEntity<String> updateDirector(@PathVariable int directorId, @RequestBody Director newDirector) {

        Director director = directorRepository.findById(directorId).get();

        director.setBio(newDirector.getBio());
        director.setBirthDate(newDirector.getBirthDate());
        director.setMovies(newDirector.getMovies());
        director.setName(newDirector.getName());
        director.setPicture(newDirector.getPicture());
        director.setSurname(newDirector.getSurname());

        directorRepository.save(director);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The new director was updated successfully.");
    }
}