package com.mert.movieapp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int movieId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoryId", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;
    private String title;
    private String description;
    private double imdb;
    private Date releaseDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Actor> cast;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Director> directors;
    private String image;
    private String trailer;

    public Movie(int movieId,String trailer, Category category, String title, String description, double imdb, Date releaseDate,
            List<Actor> cast, List<Director> directors, String image) {
        this.movieId = movieId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.imdb = imdb;
        this.releaseDate = releaseDate;
        this.cast = cast;
        this.directors = directors;
        this.image = image;
        this.trailer = trailer;
    }

    public Movie(){}

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
   


    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    

    

    
}
