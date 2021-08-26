package com.poly.shop.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.shop.bean.Category;
import com.poly.shop.service.CategoryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping()
	public List<Category> getAll(){
		return categoryService.findAll();
	}
	
	@GetMapping("{categoryId}")
	public Category getByid(@PathVariable("categoryId") Long categoryId) {
		return categoryService.findById(categoryId).get();
	}
	
	@PostMapping()
	public ResponseEntity<Category> post(@Valid @RequestBody Category category) {
		Category cate = categoryService.save(category);
		return new ResponseEntity<Category>(cate,HttpStatus.CREATED);
	}
	
	@PutMapping("{categoryId}")
	public Category put(@PathVariable("categoryId") Long categoryId, @RequestBody Category category) {
		return categoryService.save(category);
	}
	
	@DeleteMapping("{categoryId}")
	public void delete(@PathVariable("categoryId") Long categoryId) {
		categoryService.deleteById(categoryId);
	}
}
