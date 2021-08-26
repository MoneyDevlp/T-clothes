package com.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.shop.bean.Product;
import com.poly.shop.bean.Report;

public interface ProductService {

	void deleteById(Long id);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	Page<Product> findAll(Pageable pageable);

	<S extends Product> S save(S entity);

	Page<Product> findByNameContaining(String name, Pageable pageable);

	List<Report> getInventoryByCategory();

	Page<Product> findByCategoryId(Long cid, Pageable pageable);

	Page<Product> findByUnitPriceBetween(double minPrice, double maxPrice, Pageable pageable);

	Page<Product> findByProduct(Pageable pageable);

	Page<Product> findByProductAsc(Pageable pageable);

}
