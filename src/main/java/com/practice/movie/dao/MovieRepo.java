package com.practice.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.movie.entity.Movie;

public interface MovieRepo extends JpaRepository <Movie, Integer>{

}
