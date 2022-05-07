package com.mert.movieapp.controller;


import com.mert.movieapp.model.Actor;
import com.mert.movieapp.repository.ActorRepository;

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
public class ActorController {

    private final ActorRepository actorRepository;

    public ActorController(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    @GetMapping("/actor")
    public Iterable<Actor> getActor() {
        return actorRepository.findAll();
    }

    @GetMapping("/actor/{actorId}")
    public Actor getActorById(@PathVariable int actorId) {
         Actor actor = actorRepository.findById(actorId).get();
         return actor;
    }

    @PostMapping("/actor")
    public ResponseEntity<String> addActor(@RequestBody Actor actor) {
        actor.setName(actor.getName().trim());
        actorRepository.save(actor);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The actor is inserted successfully");
    }

    @PostMapping("/actor/{actorId}")
    public ResponseEntity<String> updateActor(@PathVariable int actorId, @RequestBody Actor newActor) {

        Actor actor = actorRepository.findById(actorId).get();

        actor.setBio(newActor.getBio());
        actor.setBirthDate(newActor.getBirthDate());
        actor.setMovies(newActor.getMovies());
        actor.setName(newActor.getName());
        actor.setPicture(newActor.getPicture());
        actor.setSurname(newActor.getSurname());

        actorRepository.save(actor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("The new actor was updated successfully.");
    }
}