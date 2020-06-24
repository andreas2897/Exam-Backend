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


@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private MovieRepo movieRepo;
	
	@GetMapping
	public Iterable<Category> getAllCategory(){
		return categoryRepo.findAll();
	}
	
	@PostMapping
	public Category addCategory(@RequestBody Category category) {
		return categoryRepo.save(category);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategory(@PathVariable int id) {
		Category findCategory = categoryRepo.findById(id).get();
		
		findCategory.getMovies().forEach(movie -> {
			movie.getCategories().remove(findCategory);
			movieRepo.save(movie);
		});
		categoryRepo.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public Category updateCategory(@RequestBody Category category, @PathVariable int id) {
		Category findCategory = categoryRepo.findById(id).get();
		category.setId(id);
		category.setMovies(findCategory.getMovies());
		return categoryRepo.save(category);
	}
}
