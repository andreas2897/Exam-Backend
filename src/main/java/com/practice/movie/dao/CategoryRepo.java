package com.practice.movie.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.movie.entity.Category;

public interface CategoryRepo extends JpaRepository <Category, Integer>{

}
