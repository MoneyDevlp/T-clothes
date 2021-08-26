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

import com.poly.shop.bean.Product;
import com.poly.shop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping()
	public ResponseEntity<List<Product>> getAll(){
		List<Product> listProduct = productService.findAll();
		return  ResponseEntity.ok(listProduct);
	}
	
	@PostMapping()
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		Product pro = productService.save(product);
		return new ResponseEntity<Product>(pro,HttpStatus.CREATED);
	}
	
	@PutMapping("{productId}")
	public Product update(@PathVariable("productId") Long productId,@RequestBody Product product) {
		return productService.save(product);
	}
	
	@GetMapping("{productId}")
	public Product getOne(@PathVariable("productId") Long productId) {
		return productService.findById(productId).get();
	}
	
	@DeleteMapping("{productId}")
	public void delete(@PathVariable("productId") Long productId) {
		productService.deleteById(productId);
	}

}
