package com.practice.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.movie.dao.CategoryRepo;
import com.practice.movie.dao.MovieRepo;
import com.practice.movie.entity.Category;
import com.practice.movie.entity.Movie;

@RestController
@RequestMapping("/movie")
@CrossOrigin
public class MovieController {
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@GetMapping
	public Iterable<Movie> getMovie() {
		return movieRepo.findAll();
	}
	
	@PostMapping
	public Movie addMovie(@RequestBody Movie movie) {
		return movieRepo.save(movie);
	}
	
	// connect movie - project
	@PostMapping("/{movieId}/category/{categoryId}")
	public Movie addCategoryToMovie(@PathVariable int movieId, @PathVariable int categoryId ) {
		Movie findMovie = movieRepo.findById(movieId).get();
		Category findCategory = categoryRepo.findById(categoryId).get();
		
		findMovie.getCategories().add(findCategory);
		
		return movieRepo.save(findMovie);
	}
	// disconnect movie - project
	@DeleteMapping("/{movieId}/category/{categoryId}")
	public Movie removeCategoryToMovie(@PathVariable int movieId, @PathVariable int categoryId) {
		Movie findMovie = movieRepo.findById(movieId).get();
		Category findCategory = categoryRepo.findById(categoryId).get();
		
		findMovie.getCategories().remove(findCategory);
		
		return movieRepo.save(findMovie);
	}
	
	@PutMapping("/{id}")
	public Movie updateMovie(@RequestBody Movie movie, @PathVariable int id) {
		Movie findMovie = movieRepo.findById(id).get();
		movie.setId(id);
		movie.setCategories(findMovie.getCategories());
		return movieRepo.save(movie);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMovie(@PathVariable int id) {
		movieRepo.deleteById(id);
	}
	
	

}
