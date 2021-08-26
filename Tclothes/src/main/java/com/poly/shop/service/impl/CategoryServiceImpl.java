package com.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.shop.bean.Category;
import com.poly.shop.dao.CategoryDao;
import com.poly.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public <S extends Category> S save(S entity) {
		return categoryDao.save(entity);
	}

	@Override
	public void deleteById(Long id) {
		categoryDao.deleteById(id);
	}

	@Override
	public Category getById(Long id) {
		return categoryDao.getById(id);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryDao.findById(id);
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryDao.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryDao.findAll(sort);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryDao.findAll(example, pageable);
	}

	@Override
	public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
		return categoryDao.findAll(example, sort);
	}
	
	
	
	
}
