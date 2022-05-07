package com.mert.movieapp.repository;

import com.mert.movieapp.model.Category;

import org.springframework.data.repository.CrudRepository;


public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
    
}