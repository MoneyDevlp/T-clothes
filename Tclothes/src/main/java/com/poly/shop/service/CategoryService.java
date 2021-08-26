package com.poly.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.poly.shop.bean.Category;

public interface CategoryService {

	List<Category> findAll();

	Category getById(Long id);

	void deleteById(Long id);

	<S extends Category> S save(S entity);

	Optional<Category> findById(Long id);

	<S extends Category> List<S> findAll(Example<S> example, Sort sort);

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

}
