package com.mert.movieapp.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Table(name = "director")
public class Director extends Person {
    private String picture;
    private String bio;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies;

    public Director(int id, String name, String surname, Date birthDate, String picture, String bio,
            List<Movie> movies) {
        super(id, name, surname, birthDate);
        this.picture = picture;
        this.bio = bio;
        this.movies = movies;
    }

    public Director(){}
 
 

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    

    
    
}
